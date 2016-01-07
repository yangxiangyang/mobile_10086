package com.mobile.test;

import com.mobile.util.Md5Util;

public class testMd5 {
	
	public static void main(String[] args) {
		
//		String key="jcgOfjgsHR2AFEvxZu330WyWMWcVlY5T";
//		Long intkey=Long.parseLong(key);
//		System.out.println(intkey);
		
		String str =1+"jcgOfjgsHR2AFEvxZu330WyWMWcVlY5T";
		String md5 = Md5Util.getMD5(str);
//		int uid_sign=Integer.parseInt(md5);
		
//		System.out.println(uid_sign);
		System.out.println(md5);
	}

}
