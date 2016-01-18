package com.mobile.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtil { 
	
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public static void main(String[] args) {
		HttpServletUtil servletUtil=new HttpServletUtil();
		String remoteAddr = servletUtil.request.getRemoteAddr();
		System.out.println(remoteAddr);
	}


}
