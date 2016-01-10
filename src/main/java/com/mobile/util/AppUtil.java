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
	 * 功能：获取APP名称
	 * 功能：获取APP图标和APP下载路径
	 * 作者：yangxiangyang
	 * 时间：2016年1月9日上午11:44:49
	 */
	public static String getAppMsg(String url){
		String appname="";
		try {
			//解析地址
			Parser parser=new Parser("http://mm.10086.cn/android/info/300002825368.html?from=www&fw=529#hotcom");
			//设置编码
			parser.setEncoding("UTF-8");
//		获取应用名称
			TagNameFilter tagNameFilter=new TagNameFilter("title");
			NodeList nodeList = parser.parse(tagNameFilter);
			String titleString = nodeList.elementAt(0).toPlainTextString();
			appname = titleString.substring(0,titleString.lastIndexOf("-"));
//			System.out.println("appname="+appname);
			
			
			
			//清空
			parser.reset();
			//获取图标
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
