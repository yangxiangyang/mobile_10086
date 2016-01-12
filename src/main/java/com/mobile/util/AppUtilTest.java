package com.mobile.util;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.parserapplications.filterbuilder.Filter;
import org.htmlparser.util.NodeList;

import com.mobile.app.bean.App;
import com.mobile.app.service.AppService;

public class AppUtilTest {
	
	
	/**
	 * ���ܣ���ȡAPP����
	 * ���ܣ���ȡAPPͼ���APP����·��,����
	 * ���ߣ�yangxiangyang
	 * ʱ�䣺2016��1��9������11:44:49
	 */
	public static void getAppMsg(String url,AppService appService){
		
		App app=new App();
		
//		Map map=new HashMap();
		
//		String urltest="http://mm.10086.cn/android/info/300002825368.html?from=www&fw=529#hotcom";
//		String appname="";
		try {
			//������ַ
			Parser parser=new Parser(url);
			//���ñ���
			parser.setEncoding("UTF-8");
			
			//���
			
			
			for (int i = 1; i <=3; i++) {
				parser.reset();
				TagNameFilter tagNameFilter=new TagNameFilter("div");
				HasAttributeFilter attributeFilter = new HasAttributeFilter("id", "mj_tu_"+i);
				AndFilter andFilter=new AndFilter(tagNameFilter, attributeFilter);
				NodeList nodeList = parser.parse(andFilter);
				if(nodeList.size()>0){
					
					TagNode tagNode = (TagNode) nodeList.elementAt(0);
					TagNode firstChild = (TagNode)tagNode.getFirstChild();
					String picurl = firstChild.getAttribute("src");
					System.out.println("picurl="+picurl);
//					HttpUtil.httpDownload(picurl, "E:\\AppFiles\\pictures\\"+picurl.substring(picurl.lastIndexOf("/")+1));
					
				}
			}
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
//		getAppMsg("http://mm.10086.cn/android/info/300002510083.html?from=www&fw=507", null);
		getAppMsg("http://mm.10086.cn/android/info/300000863435.html?from=www&fw=507", null);
	}
	

}
