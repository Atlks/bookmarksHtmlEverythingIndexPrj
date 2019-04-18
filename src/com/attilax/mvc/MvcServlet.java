package com.attilax.mvc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.log4j.Logger;

import com.attilax.util.ExUtilV2t33;

import aOPtool.preSvr_adminPubScrpt2publishtool2;

public class MvcServlet implements Servlet {
	final static Logger logger = Logger.getLogger(MvcServlet.class);
	private Map<String, MethodObj> url_method_maps;

	public MvcServlet(Map<String, MethodObj> url_method_maps) {
		this.url_method_maps=url_method_maps;
	}

	public MvcServlet(List list) {
		Map<String, MethodObj> url_method_maps = MvcUtil.get_url_out_mapper(list);
		this.url_method_maps=url_method_maps;
		logger.info(url_method_maps);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		 System.out.println("server....service()");
			HttpServletRequest httpServletRequest = (HttpServletRequest) arg0;
			HttpServletResponse httpServletResponse = (HttpServletResponse) arg1;
			String uri = httpServletRequest.getRequestURI();
			System.out.println(uri);
			if (MvcUtil.url_method_maps.get(uri) == null && this.url_method_maps.get(uri)==null) {
				 
				return;
			}
			 
			MethodObj MethodObj1 =this. url_method_maps.get(uri);

			Object classObj;
			try {
				classObj = ConstructorUtils.invokeConstructor(MethodObj1.classProp, null);
				Method meth = MethodObj1.methodProp;
				Object ivkrzt = meth.invoke(classObj, httpServletRequest, httpServletResponse);
				// outputHtml(httpServletResponse, r);
				System.out.println("==ivkrzt:" + ivkrzt);
				return;  
			} catch ( Exception e) {
				ExUtilV2t33.throwExV2(e);
			}
			


	}

}
