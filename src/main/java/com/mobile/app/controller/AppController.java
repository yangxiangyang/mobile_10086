package com.mobile.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobile.app.bean.App;
import com.mobile.app.service.AppService;
import com.mobile.util.AppUtil;

@Controller
@RequestMapping("app")
public class AppController {
	
	@Autowired
	private AppService appService;
	
	/**
	 * 
	 * 功能：解析网页
	 * 作者：yangxiangyang
	 * 时间：2016年1月8日上午11:21:32
	 */
	@RequestMapping("analysisNet")
	public void analysisNet(String url,HttpServletRequest request){
		Map map = AppUtil.getAppMsg(url);
		
		System.out.println(map);
		App app=new App();
		app.setAppname((String) map.get("appname"));
		
//		List list= map.get("list");
		
		
	}
	
	@RequestMapping("analysisPage")
	public String analysisPage(){
		return "app/analysisPage";
	}

	
}
