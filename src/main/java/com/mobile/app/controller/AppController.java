package com.mobile.app.controller;

import java.util.Map;

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

import com.mobile.app.bean.App;
import com.mobile.app.bean.AppThread;
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
	/*@RequestMapping("analysisNet")
	public void analysisNet(String url,HttpServletRequest request){
		Map map = AppUtil.getAppMsg(url);
		
		System.out.println(map);
		App app=new App();
		app.setAppname((String) map.get("appname"));
		
//		List list= map.get("list");
		
		
	}*/
	
	@RequestMapping("analysisPage")
	public String analysisPage(){
		return "app/analysisPage";
	}
	
	@RequestMapping("analysisAllNet")
	public void analysisAllNet(){
		
		//获取总页数
		//计算每个线程的页数
		//计算启动的数量
		//启动线程
		
		try {
			Parser parser=new Parser("http://mm.10086.cn/android/software/qbrj");
			parser.setEncoding("UTF-8");
			
			TagNameFilter tagNameFilter=new TagNameFilter("a");
			HasAttributeFilter hasAttributeFilter=new HasAttributeFilter("class","last");
			AndFilter andFilter=new AndFilter(tagNameFilter, hasAttributeFilter);
			NodeList nodeList = parser.parse(andFilter);
			TagNode tagNode = (TagNode) nodeList.elementAt(0);
			int totalPages=Integer.parseInt(tagNode.getAttribute("href").split("=")[1]);
			int threadSize=15;
			int threadNum = totalPages / threadSize + (totalPages % threadSize == 0 ? 0:1);
			for (int i = 0; i < threadNum; i++) {
				AppThread appthread=new AppThread("线程"+i,(i*15+1),appService);
				appthread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	
}
