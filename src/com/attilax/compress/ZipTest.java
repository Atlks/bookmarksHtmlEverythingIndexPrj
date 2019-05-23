package com.attilax.compress;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.attilax.io.DirTraveService;
import com.attilax.io.PathService;
import com.attilax.util.timestampUtil;
import com.cnhis.cloudhealth.module.log.Cls1;
import com.google.common.base.Function;

public class ZipTest {

public static Logger logger = Logger.getLogger(ZipTest.class);
	
	public static void main(String[] args) {
		DirTraveService dts=new DirTraveService();
	String zipFilePath="c:\\0000upzip\\firdir.zip";
	 zipFilePath="c:\\0000upzip\\honurse WebContent v2 s510.zip";
	
		String targetPath="c:\\0000upzippp";
		//final String dir = "C:\\0wkspc\\移动医疗源码\\移动医护\\移动护士站\\honurse\\holib";
	//	t2(dts);
		ZipUtil.unzipZipoisit(zipFilePath);
		System.out.println("--");
	}

	private static void t2(DirTraveService dts) {
		final String dir = "C:\\0wkspc\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\honurse\\WEB-INF\\lib";
		
		
		dts.trave_throwEx(dir, new Function<File, Object>() {

			@Override
			public Object apply(File arg0) {
				 
				String f = arg0.getAbsolutePath();
				logger.info("arg0:"+f);
				if(f.toLowerCase().trim().endsWith(".jar"))
				{
				System.out.println(ZipUtil. unzip_filelist(f));
				}
				return null;
			}
		}, new Function<Integer, Object>() {

			@Override
			public Object apply(Integer cnt_index) {
			//	Map m=Maps.newConcurrentMap();m.put("index", cnt_index)
				logger.info("cnt_index:"+cnt_index);
				return null;
			}});
	}
}
