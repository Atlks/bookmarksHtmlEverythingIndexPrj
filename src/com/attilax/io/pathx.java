package com.attilax.io;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

//import net.sf.ehcache.CacheException;















 
//import com.chenlb.mmseg4j.Dictionary;

/**
 * 
 * 
 *  /usr/java/jdk1.6.0_21/bin/java   -Djava.ext.dirs=/imServer/lib -classpath  .:/usr/java/jdk1.6.0_21/lib/dt.jar:/usr/java/jdk1.6.0_21/lib/tools.jar:/imServer/WebRoot/WEB-INF/classes com.attilax.io.pathx
 * @author caixian
 *
 */
public class pathx {

	/**linux
	 * file:/root/

file:/root/
null
file:/root/
-------1-------
file:/root/
null
file:/root/
-------2-------
file:/root/
null
file:/root/
----
file:/imServer/WebRoot/WEB-INF/classes/com/attilax/io/
file:/root/
file:/imServer/WebRoot/WEB-INF/classes/com/attilax/io/


------//////////////windows
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
null
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
-------1-------
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
null
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
-------2-------
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
null
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
----
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/com/attilax/io/
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/com/attilax/io/

	 * @param args
	 */
	public static void main1(String[] args) {
	
		   System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));  
			
		   System.out.println(Thread.currentThread().getContextClassLoader().getResource("."));  
			
		   System.out.println("-------1-------");
		 
	    //    System.out.println(Thread.class.getClassLoader().getResource("")); 
	       
	        System.out.println(pathx.class.getClassLoader().getResource("."));
	        System.out.println("-------2-------");
	        System.out.println(ClassLoader.getSystemResource(""));  
	        
	        System.out.println(ClassLoader.getSystemResource(".")); 
	        System.out.println("----");
	        System.out.println(pathx.class.getResource(""));  
	        System.out.println(pathx.class.getResource("/"));
	        System.out.println(pathx.class.getResource("."));
	        System.out.println("=============");
//	        ----
//	        file:/D:/workspace/vodx/WebRoot/WEB-INF/classes/com/attilax/io/
//	        file:/D:/workspace/vodx/WebRoot/WEB-INF/classes/
//	        file:/D:/workspace/vodx/WebRoot/WEB-INF/classes/com/attilax/io/
//	        =============
	        
	        
	        if("1"=="1")return;
	  
	    //  System.out.println(path_sub);  

	        System.out.println(  classPath());
	   //--------------------------------------------------     
	        System.out.println("===mmseg path:");
	        System.out.println("o11:"+classPath());
	        //linux path ok ,but windows   /d:/xxx.dat. daeleig / in front
	        System.out.println("o9:"+pathx.classPath()+"/QQWry.dat");
	   //     System.out.println(  CacheException.class.getClassLoader().getResource(""));
	        //this null in win and in linux at o3
//	        System.out.println( "o6:"+ Dictionary.class.getClassLoader().getResource("data"));
//	        Dictionary .getDefalutPath();
	 //       System.out.println("o4:"+mycls.getpath());
	        
	        
	        //flow cant use in windows
	 	   System.out.println(Thread.currentThread().getContextClassLoader().getResource("/"));  
	 	  System.out.println(pathx.class.getClassLoader().getResource("/"));
	 	  System.out.println(ClassLoader.getSystemResource("/"));	 	
	 	  System.out.println("web app path:"+webAppPath());
	 		
	}
	
	public static void main(String[] args) {
		System.out.println(isInWebPrj());
		System.out.println(new File("E:\\333%20click%e6%b1%89%e5%ad%97").exists());
		System.out.println(	 new File( URLDecoder.decode("E:\\333%20click%e6%b1%89%e5%ad%97")).exists());
		System.out.println(  pathx.class.getResource("").getPath() );
		System.out.println(  pathx.class.getResource(".").getPath() );
		System.out.println(  pathx.class.getResource("./").getPath() );
		System.out.println(classPath());
	}
	
	 /**
	@author attilax 鑰佸搰鐨勭埅瀛�
	\t@since  Aug 3, 2014 4:39:13 AM$
	
	 * @param f
	 * @return
	 */
	public static boolean isFile(String f) {
		// attilax 鑰佸搰鐨勭埅瀛�  4:39:13 AM   Aug 3, 2014 
		
		{ 
			
		return !f.endsWith("\\");
		 } 
		
		
	}
	public static String classpath;
	//todox class path o5
	public static String classPath()
	{
		
	//	new File("/").getAbsolutePath()
//		if(isWindows())
//		{
//	String s=		pathx.class.getClassLoader().getResource("/").getPath();
//		     URL resource = pathx.class.getResource("/");
//			String path_x = resource.getPath();
//			String path=path_x.substring(1,path_x.length()-1);
//			return path;
//		}
		//URL u=new URL()
		//jeoig linux hamyar cheng leig ***.jar! le ..
	//	return new File(  classpathGetter.class.getResource("").getPath()).getParent() ;  
	    String path ;
	    	try {
	    		path=pathx.class.getResource("").getPath();
			} catch (Exception e) {
				try {
					 path= pathx.class.getResource(".").getPath();
				} catch (Exception e2) {
					 path= pathx.class.getResource("./").getPath();
					// core.log(e2);
				}
			
			}	
	    	classpath=path;
	    		
	    
//	    System.out.println(  );
//		System.out.println(  );
		int index=path.lastIndexOf("/");
		String path_sub=path.substring(0, index-15);
		
		
		try {
			path_sub=	URLDecoder.decode(path_sub,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path_sub;

	}
	
	/**
	 *    or /C:/workspace/vodx/WebRoot/WEB-INF/classes/
	@author attilax 鑰佸搰鐨勭埅瀛�
		@since  2014-9-1 涓婂崍01:12:52   
	
	 * @param cls
	 * @return
	 */
	public static String classPath(Class<?> cls)
	{
	String path = cls.getResource("").getPath();
	try {
		path=	URLDecoder.decode(path,"utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 	path;
	}
	

	public static boolean isWindows() {
	//	core.logger.info("System.getProperty(os.name)"+System.getProperty("os.name"));
	String os=	System.getProperty("os.name").toLowerCase().trim();
	if(os.contains("window"))
		return true;
	else
		return false;
	}

	/**
	  /webapp
	@author attilax 鑰佸搰鐨勭埅瀛�
		@since  o8h m_u_47   
	
	 * @param request
	 * @return
	 */
	public static String webAppPath_webfmt(HttpServletRequest request) {
		
		return ""+request.getContextPath();
	}
	
	/**
	  http://host/webapp
	@author attilax 鑰佸搰鐨勭埅瀛�
		@since  o8h m_u_47   
	
	 * @param request
	 * @return
	 */
	public static String webAppPath_httpFmt(HttpServletRequest request) {
		// request.getScheme()+
		String path = request.getContextPath();
		String basePath ="http://"+request.getServerName()+":"+request.getServerPort()+path+"";
		return  basePath;
	}


	
	public static String webAppPath(HttpServletRequest request) {
		
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/** web-inf parent dir...zosh   web-inf../
	 * c:/aaaa/ fmt
	@author attilax 鑰佸搰鐨勭埅瀛�
		@since  o8h m_s_d   
	
	 * @return
	 */
	public static String webAppPath() {
		String path= classPath();
		File file = new File(path);
		
		return file.getParentFile().getParent();
	}
	/**
	 *   replace sepection cchar
	@author attilax 鑰佸搰鐨勭埅瀛�
		@since  o7d Y3749$
	
	 * @return
	 */
	public static String webAppPath_jensyegeor() {
		String path= classPath();
		File file = new File(path);
		
		String parent = file.getParentFile().getParent();
		String s=parent.replaceAll("\\\\", "\\/");
		return s;
	}
	
	public static String classPathParentV2() {
	 
	String classpat=	pathx.classPath();
		
		try {
			classpat=URLDecoder.decode(classpat,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String parent = new File(classpat).getParent();
		try {
			parent=URLDecoder.decode(parent,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  parent;
	}

	public static String classPathParent() {
		// TODO Auto-generated method stub
		if(isWebPathMode)
			return webAppPath();
	String classpat=	pathx.classPath();
		
		try {
			classpat=URLDecoder.decode(classpat,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String parent = new File(classpat).getParent();
		try {
			parent=URLDecoder.decode(parent,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  parent;
	}
	
	public static boolean isWebPathMode=true;
	
	public static String classPathParent_jensyegeor() {
		
		//ati p115
if(isWebPathMode)
	return webAppPath();
		// TODO Auto-generated method stub
		String parent = new File(pathx.classPath()).getParent();
		try {
			parent=URLDecoder.decode(parent,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parent=parent.replaceAll("\\\\", "\\/");
		return  parent;
	}


	public static String replacePath(String path, String wait2replacePathFix, String newStr) {
		String replaceStr = wait2replacePathFix.replaceAll("\\\\", "\\\\\\\\") ;
		path = path.replaceAll(replaceStr, newStr);
		return path;
	}
	public static String convertSlashPath(String path) {
		return path.replaceAll("\\\\", "/");
	}
	public static String fixSlash(String path) {
		path=	replacePath(path, "\\", "/");
		return path;
	}

//		public static String convertSlashPath(String path) {
//		return path.replaceAll("\\\\", "/");
//	}
	public static String appPath() {
		// TODO Auto-generated method stub
		return webAppPath();
	}

	public static String getTextContext(String string) {
		pathx.isWebPathMode=true;
		String bat=pathx.classPathParent()+"/"+string;
		bat=bat.replace("/", "\\");
		String t = null;//=filex.read_SF(bat, "");
		return t;
	}

	public static String addDoubleQuoue(String urlname_full) {
		// TODO Auto-generated method stub
		return "\""+urlname_full+"\"";
	}

	/**
	attilax    2016年5月7日  下午8:44:37
	 * @param s
	 * @return
	 */
	public static String processPreVar(String s) {
		 String root= approot();
		 s=s.replace("$root$", root);
		 return s;
	}

	private static String approot() {
		if(isInWebPrj())
			 return webAppPath();
		 
		return classPath();
	}
	
		public static String fixSlash2reversSplash(String path) {
			path=	path.replace('/', '\\');//.replace('/', '\\')
			return path;
		}

		public static String fillFullpath(String output) {
			if(output.startsWith("/"))
				output=output.substring(1);
			 if(!output.contains(":"))
				 output=pathx.webAppPath()+"/"+output;
			return output;
		}
			/**
		attilax    2016骞�5鏈�3鏃�  涓嬪崍8:36:38
		 * @param string
		 * @param js
		 * @return
		 */
		public static String join(String dir, String f) {
			f=f.trim();
			if(f.startsWith("/"))
				f=f.substring(1);
			String s=dir+"/"+f;
			s=s.replace("$web_app_root$", pathx.webAppPath());
			return s;
		}

		public static String appPath_webPrjMode() {
			 
			return new File(appPath()).getParent();
		}


	public static String prjPath() {
			 String webroot=webAppPath_jensyegeor();
			return new File(webroot).getParent(); 
		}
	
	public static String prjPathV2() {
		// String webroot=webAppPath_jensyegeor();
		return new java.io.File("").getAbsolutePath(); 
	}
	
	
	/**
	 * class is bin
	 * @return
	 */
	public static String prjPath_semode() {
		 String webroot=classPathParentV2();
		return webroot;
		//new File(webroot).getParent(); 
	}

		public static String prjPath_webrootMode() {
			 String webroot=webroot();
			return  new File(webroot).getParent();
		}
	/**
	attilax    2016年5月7日  下午8:46:37
	 * @return
	 */
	private static boolean isInWebPrj() {
		 String s=classPath();
		 File dir=new File(s);
		 File paren=new File(s).getParentFile();
		 if(dir.getName().equals("classes") && paren.getName().equals("WEB-INF"))
		return true;
		 else
		 return false;
	}

		private static String webroot() {
			String path= classPath();
			File file = new File(path);
			
			String parent = file.getParentFile().getParent();
			String s=parent.replaceAll("\\\\", "\\/");
			return s;
		//	return null;
		}

		public static String prjParentPath_webrootMode() {
			// TODO Auto-generated method stub
			return new File(prjPath_webrootMode()).getParent();
		}

		public static String webrootPath() {
			// TODO Auto-generated method stub
			return webroot();
		}
		
			public static String webinfDir() {
			
			return classPathParent();
		}

			public static String appPathV2() {
				String path= classPath();
				File file = new File(path);
				
				String parent = file.getParent();
			//	String s=parent.replaceAll("\\\\", "\\/");
			//	return s;
					return parent;
			}
			public static String getFileName_noExtName(String fileName) {
				String name = "";
				String extention = "";
				int lastSpashIndex = fileName.lastIndexOf(File.separator);
				//qaf
				String fname = fileName.substring(lastSpashIndex + 1);

				int i = fname.lastIndexOf(".");
				
				try {
					name = fname.substring(0, i); // --閺傚洣娆㈤崥锟�
				} catch (Exception e) {
					if (i == -1) // no ext
						return fname;
				}
			
				// extention = fileName.substring(i + 1); // --閹碘晛鐫嶉崥锟�

				return name;

			}
			/**
			 * jeig haosyo you wenti l .qb25
			 * @param fileName
			 * @return
			 */
			public static String getFileName_mainname_noExtName_nopath(String fileName) {
				String f=getFileName_noExtName(fileName);
				String substring = f.substring(  f.lastIndexOf(".")+1,f.length());
				return substring;

			}
			/**
			 * _mainname_noExtName_nopath
			 * @param fileName
			 * @return
			 */
			public static String getFileName_mainname(String fileName) {
				String f=getFileName_noExtName(fileName);
				String substring = f.substring(  f.lastIndexOf("/")+1,f.length());
				return substring;

			}
}
