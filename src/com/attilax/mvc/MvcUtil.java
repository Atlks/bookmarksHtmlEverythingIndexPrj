package com.attilax.mvc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import com.attilax.util.ExUtilV2t33;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MvcUtil {
	
	public static Object[] getArgs(Method meth, ServletRequest arg0, ServletResponse arg1) {
		List li=Lists.newArrayList();
		Parameter[] Parameters_arr=	meth.getParameters();
		for (Parameter parameter : Parameters_arr) {
			Class<?> type1 = parameter.getType();
			if  (  type1 ==HttpServletRequest.class)
			{
				li.add(arg0);
			}
		}
		return li.toArray();
	}
	public static void outputHtml(HttpServletResponse httpServletResponse, String txt,String encode)   {
		try {
			byte[] s = txt.getBytes(encode);
			httpServletResponse.setCharacterEncoding(encode);
			ServletOutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(s);
	 		outputStream.flush();
		} catch (Exception e) {
			ExUtilV2t33.throwExV2(e);
		}
		
	}
	public static void outputHtml(HttpServletResponse httpServletResponse, String txt)   {
		try {
			byte[] s = txt.getBytes("utf8");
			httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
			httpServletResponse.setCharacterEncoding("utf8");
			ServletOutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(s);
	 		outputStream.flush();
		} catch (Exception e) {
			ExUtilV2t33.throwExV2(e);
		}
		
	}
//	public static  Map<String, MethodObj> get_url_out_mapper() {
//		Map<String, MethodObj> url_method_maps = Maps.newLinkedHashMap();
//		ClassListForeach(new Consumer<Class<?>>() {
//
//			@Override
//			public void accept(Class<?> cls) {
//
//				MyComponent MyComponentAnno = (MyComponent) cls.getAnnotation(MyComponent.class);
//				if (MyComponentAnno == null)
//					return;
//
//				// ��ȡһ�����ע�⣬����ע����
//				Method[] methds = cls.getMethods();
//				for (Method method : methds) {
//					MyMapping anno1 = (MyMapping) method.getAnnotation(MyMapping.class);
//					if (anno1 == null)
//						continue;
//					String uri_mapping = anno1.value();
//					MethodObj MethodObj1 = new MethodObj();
//					MethodObj1.classProp = cls;
//					MethodObj1.methodProp = method;
//					url_method_maps.put(uri_mapping, MethodObj1);
//				}
//
//			}
//		});
//
//		return url_method_maps;
//	}

//	private static void ClassListForeach(Consumer<Class<?>> consumer) {
//		List<Class<?>> li_allclass = Lists.newArrayList();
//		li_allclass.add(MvcCtrol.class);
//		for (Class class1 : li_allclass) {
//			consumer.accept(class1);
//		}
//
//	}
	public static	Map<String, MethodObj> url_method_maps=Maps.newLinkedHashMap();

	public static Map<String, MethodObj> get_url_out_mapper(List<Class<?>> list) {
		Map<String, MethodObj> url_method_maps = Maps.newLinkedHashMap();
		for (Class<?> cls : list) {
			Method[] methds = cls.getMethods();
			for (Method method : methds) {
				Path anno1 = (Path) method.getAnnotation(Path.class);
				if (anno1 == null)
					continue;
				String uri_mapping = anno1.value();
				MethodObj MethodObj1 = new MethodObj();
				MethodObj1.classProp = cls;
				MethodObj1.methodProp = method;
				url_method_maps.put(uri_mapping, MethodObj1);
			}
		}
		
		return url_method_maps;
	}

}
