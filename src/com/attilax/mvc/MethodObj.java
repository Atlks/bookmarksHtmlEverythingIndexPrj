package com.attilax.mvc;

import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

public class MethodObj {

	public Class<?> classProp;
	public Method methodProp;
	
	public String  toString()
	{
		return JSON.toJSONString(this);
		
	}

	public Class<?> getClassProp() {
		return classProp;
	}

	public void setClassProp(Class<?> classProp) {
		this.classProp = classProp;
	}

	public Method getMethodProp() {
		return methodProp;
	}

	public void setMethodProp(Method methodProp) {
		this.methodProp = methodProp;
	}

}
