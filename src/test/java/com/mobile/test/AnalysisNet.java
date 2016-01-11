package com.mobile.test;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;

import com.mobile.util.HttpUtil;

public class AnalysisNet {
	public static void main(String[] args) {
		

		try {
			//解析地址
			Parser parser=new Parser("http://mm.10086.cn/android/info/300002825368.html?from=www&fw=529#hotcom");
			//设置编码
			parser.setEncoding("UTF-8");
			
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
			String apkicon = tagNode.getAttribute("src");
			String filename=apkicon.substring(apkicon.lastIndexOf("/")+1);
			
			//获取服务器根路径,把图片放到服务器文件夹下
//			HttpUtil.httpDownload(apkicon, "E:\\AppFiles\\"+filename);
			
//			System.out.println(filename);
			
			parser.reset();
			//获取应用下载路径  class=mj_xzdbd
			tagNameFilter=new TagNameFilter("a");
			attributeFilter = new HasAttributeFilter("class", "mj_xzdbd");
			andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList = parser.parse(andFilter);
			tagNode  = (TagNode) nodeList.elementAt(0);
			String apkurl = tagNode.getAttribute("href");
//			HttpUtil.httpDownload(apkurl, "E:\\AppFiles\\"+appname+".apk");
//			System.out.println(apkurl);
			
			parser.reset();
			//picurl	图片路径  div  id=mj_tu_1
			attributeFilter = new HasAttributeFilter("id", "mj_tu_1");
			String picname1 = getPicUrl(parser, "mj_tu_1");
			String picname2 = getPicUrl(parser, "mj_tu_2");
			String picname3 = getPicUrl(parser, "mj_tu_3");
//			System.out.println("picname1="+picname1);
//			System.out.println("picname2="+picname2);
//			System.out.println("picname3="+picname3);
			
			
			parser.reset();
			//APP详细信息mj_info font-f-yh
			tagNameFilter=new TagNameFilter("div");
			attributeFilter = new HasAttributeFilter("class", "mj_info font-f-yh");
			andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList=parser.parse(andFilter);
			tagNode = (TagNode) nodeList.elementAt(0);
			NodeList liNode = tagNode.getChildren().elementAt(0).getChildren();
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < liNode.size(); i++) {
				System.out.println("第"+i+"个元素是="+liNode.elementAt(i).toPlainTextString());
				String str=liNode.elementAt(i).toPlainTextString();
				String pro=str.substring(str.lastIndexOf("：")+1).trim();
				System.out.println("pro="+pro);
				list.add(pro);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static String getPicUrl(Parser parser,String id){
		parser.reset();
		String picname="";
		try {
			TagNameFilter tagNameFilter=new TagNameFilter("div");
			HasAttributeFilter attributeFilter = new HasAttributeFilter("id", id);
			AndFilter andFilter=new AndFilter(tagNameFilter, attributeFilter);
			NodeList nodeList = parser.parse(andFilter);
			TagNode tagNode = (TagNode) nodeList.elementAt(0);
			TagNode firstChild = (TagNode)tagNode.getFirstChild();
			String picurl = firstChild.getAttribute("src");
			picname = picurl.substring(picurl.lastIndexOf("/")+1);
			HttpUtil.httpDownload(picurl, "E:\\AppFiles\\"+picname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return picname;
	}
}
