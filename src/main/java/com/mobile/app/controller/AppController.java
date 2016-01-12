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
	 * ���ܣ�������ҳ
	 * ���ߣ�yangxiangyang
	 * ʱ�䣺2016��1��8������11:21:32
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
		
		//��ȡ��ҳ��
		//����ÿ���̵߳�ҳ��
		//��������������
		//�����߳�
		
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
				AppThread appthread=new AppThread("�߳�"+i,(i*15+1),appService);
				appthread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	
}
