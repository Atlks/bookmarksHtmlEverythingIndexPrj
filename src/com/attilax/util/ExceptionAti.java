package com.attilax.util;

import java.io.PrintStream;
 
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;

public class ExceptionAti extends RuntimeException {
	public Map data=Maps.newConcurrentMap();;
	public int HResult;
	 

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		data = data;
	}

	public int getHResult() {
		return HResult;
	}

	public void setHResult(int hResult) {
		HResult = hResult;
	}

	public Exception getInnerException() {
		return InnerException;
	}

	public void setInnerException(Exception innerException) {
		InnerException = innerException;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getStackTrace() {
		return StackTrace;
	}

	public void setStackTrace(String stackTrace) {
		StackTrace = stackTrace;
	}

	public Exception InnerException;
	public String Message;
	public String Source;
	public String StackTrace;
	public Object backtrace;
	public Object getBacktrace() {
		return backtrace;
	}
	
	 public void printStackTrace() {
	        printStackTrace(System.err);
	        
	        System.err.println(JSON.toJSONString(backtrace, true) );
	 }
	   
 

	public void setBacktrace(Object backtrace) {
		this.backtrace = backtrace;
	}

	public	String detailMessage;

	public String getDetailMessage() {
		return detailMessage;
	}

	 

	public String getStatments() {
		return statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
	List<StackTraceElement> stackTraceElementLists = Lists.newArrayList();
	public String statments;
	 
 

	public void addStackTraceElement(String cmd, int lineNum) {
		StackTraceElement ste = new StackTraceElement("", cmd, "", lineNum);
	 
		stackTraceElementLists.add(0,ste);
		this.setStackTrace(stackTraceElementLists.toArray(new StackTraceElement[stackTraceElementLists.size()]));
		
	}
	 

}
