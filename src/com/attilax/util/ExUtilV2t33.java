package com.attilax.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

//import com.attilax.io.BreakException;

 
/**
 * v2 s49
 * @author attilax
 *
 */
public class ExUtilV2t33 {

	/**
	 * 
	 * @param e3
	 */
	@Deprecated
	public static void throwEx(Throwable e3) {
		 if( e3 instanceof RuntimeException)
			  throw (RuntimeException)e3;
		  else
			  throw new RuntimeException(e3);
		
	}

//	public static void checkExFromJson(responseImp paramServletResponse) {
//		PrintWriterImp wi;
//		try {
//			wi = (PrintWriterImp)paramServletResponse.getWriter();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException(e);
//		}
//		
//		String r=wi.line;
//		try {
//			@SuppressWarnings("rawtypes")
//			Map m=	AtiJson.fromJson(r);
//			if(m.get("@type").toString().contains("Exception"))
//				throw new RuntimeException(r);
//		} catch (Exception e) {  //maybe not json fmt
//		     
//		}
//	
//		
//	}
//	
	
	public static void throwExV2(Throwable e) {
		if(e instanceof InvocationTargetException )
		{
			e=e.getCause();
		}
		 if( e instanceof RuntimeException)
			  throw (RuntimeException)e;
		  else
			  throw new RuntimeException(e);
		
	}
	
	
	public static void throwExV2(Throwable e,String msg) {
		if(e instanceof InvocationTargetException )
		{
			e=e.getCause();
		}
		 if( e instanceof RuntimeException)
			  throw (RuntimeException)e;
		  else
			  throw new RuntimeException(msg,e);
		 
		
	}
	
		public static void throwEx(String string) {
		 throw new RuntimeException(string);
		
	}
	
	public static void throwExV3(Throwable e,String msg) {
		if(e instanceof InvocationTargetException )
		{
			e=e.getCause();
		}
		
		if( e instanceof RuntimeException)
		{
			Throwable e3=e.getCause();
			 RuntimeException runtimeException = new RuntimeException(msg,e3);
			 throw runtimeException;
			//  throw (RuntimeException)e;
		}
		  else
			  throw new RuntimeException(msg,e);
		 
		
	}

	public static void throwNotImpEx() {
		throw new RuntimeException(" not imp ");
		
	}

	public static void throwExV4(String string, Object dbginfo) {
		 RuntimeExceptionAtiVer runtimeExceptionAtiVer = new RuntimeExceptionAtiVer(string);
		 runtimeExceptionAtiVer.dbginfo=dbginfo;
		throw runtimeExceptionAtiVer;
		
	}

//	public static void throwExV4(String string, BreakException e, Map dbginfo) {
//		 RuntimeExceptionAtiVer runtimeExceptionAtiVer = new RuntimeExceptionAtiVer(string);
//		 runtimeExceptionAtiVer.dbginfo=dbginfo;
//		 runtimeExceptionAtiVer.initCause(e);
//		throw runtimeExceptionAtiVer;
//		
//		
//	}

}
