package com.attilax.util;

import java.io.PrintStream;
 
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Lists;

public class ExceptionAti extends RuntimeException {
	public Object Data;
	public int HResult;
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
