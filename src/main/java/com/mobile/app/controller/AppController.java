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
	 * ���ܣ�������ҳ
	 * ���ߣ�yangxiangyang
	 * ʱ�䣺2016��1��8������11:21:32
	 */
	@RequestMapping("analysisNet")
	public void analysisNet(String netUrl,HttpServletRequest request){
		
		try {
			//������ַ
			Parser parser=new Parser(netUrl);
			//���ñ���
			parser.setEncoding("UTF-8");
			//��ȡӦ������
			//��ȡӦ������
			TagNameFilter tagNameFilter=new TagNameFilter("title");
			NodeList nodeList = parser.parse(tagNameFilter);
			String titleString = nodeList.elementAt(0).toPlainTextString();
			String appname = titleString.substring(0,titleString.lastIndexOf("-"));
//			System.out.println("appname="+appname);
			
			//���
			parser.reset();
			//��ȡͼ��
			tagNameFilter=new TagNameFilter("img");
			HasAttributeFilter attributeFilter = new HasAttributeFilter("id", "appicon");
			AndFilter andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList= parser.parse(andFilter);
			TagNode tagNode  = (TagNode) nodeList.elementAt(0);
			String apkurl = tagNode.getAttribute("src");
			String filename=apkurl.substring(apkurl.lastIndexOf("/")+1);
			
			//��ȡ��������·��
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
