package com.mobile.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobile.app.service.AppService;
import com.mobile.util.HttpUtil;

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
	public void analysisNet(String netUrl,HttpServletRequest request){
		
		try {
			//解析地址
			Parser parser=new Parser(netUrl);
			//设置编码
			parser.setEncoding("UTF-8");
			//获取应用名称
			//获取应用名称
			TagNameFilter tagNameFilter=new TagNameFilter("title");
			NodeList nodeList = parser.parse(tagNameFilter);
			String titleString = nodeList.elementAt(0).toPlainTextString();
			String appname = titleString.substring(0,titleString.lastIndexOf("-"));
//			System.out.println("appname="+appname);
			
			//清空
			parser.reset();
			//获取图标
			tagNameFilter=new TagNameFilter("img");
			HasAttributeFilter attributeFilter = new HasAttributeFilter("id", "appicon");
			AndFilter andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList= parser.parse(andFilter);
			TagNode tagNode  = (TagNode) nodeList.elementAt(0);
			String apkurl = tagNode.getAttribute("src");
			String filename=apkurl.substring(apkurl.lastIndexOf("/")+1);
			
			//获取服务器根路径
			String path = request.getSession().getServletContext().getRealPath("/");
			System.out.println("path="+path);
			
			HttpUtil.httpDownload(apkurl, "E:\\"+filename);
			
			System.out.println(filename);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@RequestMapping("analysisPage")
	public String analysisPage(){
		return "app/analysisPage";
	}

	
	
}
