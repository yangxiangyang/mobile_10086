package com.mobile.util;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class AppUtil {
	
	
	/**
	 * 
	 * ���ܣ���ȡAPP����
	 * ���ܣ���ȡAPPͼ���APP����·��
	 * ���ߣ�yangxiangyang
	 * ʱ�䣺2016��1��9������11:44:49
	 */
	public static String getAppMsg(String url){
		String appname="";
		try {
			//������ַ
			Parser parser=new Parser("http://mm.10086.cn/android/info/300002825368.html?from=www&fw=529#hotcom");
			//���ñ���
			parser.setEncoding("UTF-8");
//		��ȡӦ������
			TagNameFilter tagNameFilter=new TagNameFilter("title");
			NodeList nodeList = parser.parse(tagNameFilter);
			String titleString = nodeList.elementAt(0).toPlainTextString();
			appname = titleString.substring(0,titleString.lastIndexOf("-"));
//			System.out.println("appname="+appname);
			
			
			
			//���
			parser.reset();
			//��ȡͼ��
			tagNameFilter=new TagNameFilter("img");
			HasAttributeFilter attributeFilter = new HasAttributeFilter("id", "appicon");
			AndFilter andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList= parser.parse(andFilter);
			TagNode tagNode  = (TagNode) nodeList.elementAt(0);
			String apkicon = tagNode.getAttribute("src");
			String filename=apkicon.substring(apkicon.lastIndexOf("/")+1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
