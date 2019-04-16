package com.attilax.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileCacheManager   {

	private String cacheDir;

	public FileCacheManager(String cachedir) {
		this.cacheDir=cachedir;
	}


	
	public String get(String key) throws IOException {
	//	String basename=FilenameUtils.getName(absolutePath);
		File file2 = new File(cacheDir+"\\"+key);
		return FileUtils.readFileToString(file2);
	}

	public void set(String key, String eml2txt) throws IOException {
	//	String basename=FilenameUtils.getName(absolutePath);
		File file2 = new File(cacheDir+"\\"+key);
		FileUtils.write(file2, eml2txt);
		
	}
	
	public boolean exist(String key) {
		//String basename=FilenameUtils.getName(absolutePath);
		String cachekey = cacheDir+"\\"+key;
		File file2 = new File(cachekey);
		 
	//		return FileUtils.readFileToString(file2);
		 
		return file2.exists();
	}
	
	
	
	public boolean existCache(String absolutePath) {
		String basename=FilenameUtils.getName(absolutePath);
		String cachekey = cacheDir+"\\"+basename+".txt";
		File file2 = new File(cachekey);
		 
	//		return FileUtils.readFileToString(file2);
		 
		return file2.exists();
	}

	public String getCache(String absolutePath) throws IOException {
		String basename=FilenameUtils.getName(absolutePath);
		File file2 = new File(cacheDir+"\\"+basename+".txt");
		return FileUtils.readFileToString(file2);
	}

	public void setCache(String absolutePath, String eml2txt) throws IOException {
		String basename=FilenameUtils.getName(absolutePath);
		File file2 = new File(cacheDir+"\\"+basename+".txt");
		FileUtils.write(file2, eml2txt);
		
	}

}
