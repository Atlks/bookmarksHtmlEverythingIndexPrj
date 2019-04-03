package com.attilax.time;

import java.text.ParseException;

public abstract   class HandlerBase implements Handler {
	public String name;
	public HandlerBase(String name) {
		this.name=name;
	} 
 
	/**
	@author attilax 老哇的爪子
		@since  o7l h510$
	
	 * @param string
	 */ 
 	public void setName(String name){this.name=name;};
	public String getName( ){return this.name;};
	public  abstract  Object handleReq(Object arg) throws Exception;
}
