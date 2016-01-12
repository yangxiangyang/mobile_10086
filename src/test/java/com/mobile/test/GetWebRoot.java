package com.mobile.test;

import java.io.File;

import org.apache.taglibs.standard.extra.spath.Path;


public class GetWebRoot {
	public static void main(String[] args) {
		
		
//		String path = GetWebRoot.getClass().getClassLoader().getResource("/").getPath();
//		System.out.println(getWebRootAbsolutePath());
//		
//		File file=new File(""); 
//		String path=file.getAbsolutePath();
//		System.out.println(path);
		

//		System.out.println(System.getProperties());
		
	}
	
	
	public static  String getWebRootAbsolutePath() {    
	    String path = null;    
	    String folderPath = Path.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	    
	    System.out.println(folderPath);
	    if (folderPath.indexOf("WEB-INF") > 0) {    
	        path = folderPath.substring(0, folderPath.indexOf("WEB-INF"));     
	    }    
	    return path;    
	}    

}

