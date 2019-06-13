package aaaHttpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.alibaba.fastjson.JSON;

public class tomcatServletDemo {
	
	//http://localhost:8088/list.json
	public static void main(String[] args) throws ServletException, LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8088);
		tomcat.setBaseDir(".");

		String contextPath = "/";
		String baseDir = "D:\\workspace\\tomcatEmbServer";
		Context Context1 = tomcat.addWebapp(contextPath, baseDir);
		// tomcat.addServlet(contextPath, servletName, servlet)

		tomcat.addServlet(Context1, "servletName", new Servlet() {
			 

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
			public void service(ServletRequest arg0, ServletResponse response) throws ServletException, IOException {

				List li=new ArrayList(){{
					this.add( new HashMap(){{
						this.put("loginname", "at4445556667777");
					}});
					
					this.add( new HashMap(){{
						this.put("loginname", "name2222");
					}});
				}};
			 
						
				HttpServletResponse res=(HttpServletResponse) response;
				res.setHeader("Access-Control-Allow-Origin", "*");	
				response.getWriter().println(JSON.toJSONString(li));

			}
		});
		Context1.addServletMapping("/list.json", "servletName");
		
 
		tomcat.start();
		tomcat.getServer().await();

		System.out.println("--f");
	}

}
