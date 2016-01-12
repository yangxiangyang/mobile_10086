package com.mobile.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;

import com.mobile.app.bean.App;
import com.mobile.app.service.AppService;

public class AppUtil {
	
	
	/**
	 * 功能：获取APP名称
	 * 功能：获取APP图标和APP下载路径,评论
	 * 作者：yangxiangyang
	 * 时间：2016年1月9日上午11:44:49
	 */
	@SuppressWarnings("deprecation")
	public static void getAppMsg(String url,AppService appService){
		
		App app=new App();
		
//		Map map=new HashMap();
		
//		String urltest="http://mm.10086.cn/android/info/300002825368.html?from=www&fw=529#hotcom";
//		String appname="";
		try {
			//解析地址
			Parser parser=new Parser(url);
			//设置编码
			parser.setEncoding("UTF-8");
//		获取应用名称
			TagNameFilter tagNameFilter=new TagNameFilter("title");
			NodeList nodeList = parser.parse(tagNameFilter);
			String titleString = nodeList.elementAt(0).toPlainTextString();
			String appname = titleString.substring(0,titleString.lastIndexOf("-"));
			System.out.println("appname="+appname);
//			map.put("appname", appname);
			
			
			//清空
			parser.reset();
			//获取图标
			tagNameFilter=new TagNameFilter("img");
			HasAttributeFilter attributeFilter = new HasAttributeFilter("id", "appicon");
			AndFilter andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList= parser.parse(andFilter);
			TagNode tagNode  = (TagNode) nodeList.elementAt(0);
			String appiconurl = tagNode.getAttribute("src");
			System.out.println("appiconurl="+appiconurl);
			String appicon=appiconurl.substring(appiconurl.lastIndexOf("/")+1);
			System.out.println("appicon="+appicon);
			HttpUtil.httpDownload(appiconurl, "E:\\AppFiles\\"+appicon);
			
			
			parser.reset();
			//获取应用下载路径  class=mj_xzdbd
			tagNameFilter=new TagNameFilter("a");
			attributeFilter = new HasAttributeFilter("class", "mj_xzdbd");
			andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList = parser.parse(andFilter);
			String apkurl ="";
			if(nodeList.size()>0){
				
				apkurl = tagNode.getAttribute("href");
				System.out.println("apkurl="+apkurl);
				if(apkurl!=null){
					HttpUtil.httpDownload(apkurl, "E:\\AppFiles\\"+appname+".apk");
				}
				tagNode  = (TagNode) nodeList.elementAt(0);
			}


			parser.reset();
			//picurl	图片路径  div  id=mj_tu_1
			attributeFilter = new HasAttributeFilter("id", "mj_tu_1");
			String picurl1 = getPicUrl(parser, "mj_tu_1");
			String picurl2 = getPicUrl(parser, "mj_tu_2");
			String picurl3 = getPicUrl(parser, "mj_tu_3");
			String picname1 = picurl1.substring(picurl1.lastIndexOf("/")+1);
			String picname2 = picurl1.substring(picurl2.lastIndexOf("/")+1);
			String picname3 = picurl1.substring(picurl3.lastIndexOf("/")+1);
			
			
			//获取应用名称
			parser.reset();
			//获取评论 class= mj_pl_list_i
//			commentUser	评论人
//			commentDate	评论时间
//			comment	评论内容
//			commentNum	评分
//			appid	所属应用
			
			
			parser.reset();
			
			tagNameFilter=new TagNameFilter("div");
			attributeFilter = new HasAttributeFilter("class", "mj_pl_list_i_m");
			andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList = parser.parse(andFilter);
			for (int i = 0; i < nodeList.size(); i++) {
				tagNode = (TagNode) nodeList.elementAt(i);
				NodeList children = tagNode.getChildren();
//				commentUser	评论人
				String commentUser=children.elementAt(0).getChildren().elementAt(1).toPlainTextString();
				System.out.println("父类"+i+"的评论人-------"+commentUser);
//				comment	评论内容
				String comment = children.elementAt(1).toPlainTextString();
				System.out.println("父类"+i+"的评论内容---------"+comment);
//				commentDate	评论时间
				String commentDate = children.elementAt(2).getChildren().elementAt(1).toPlainTextString();
				System.out.println("父类"+i+"的评论时间------------"+commentDate);
				
			}
			
			
			
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
//				list.add(pro);
			}
			
//			map.put("list", list);
			
			
			
			
			
			
			
			//添加到数据库
			app.setId(WebUtils.getRandomId());
			
			app.setAppname(appname);
			
			String strPrice=liNode.elementAt(1).toPlainTextString();
			System.out.println("strPric="+strPrice.substring(strPrice.lastIndexOf("：")+1).trim());
			app.setPrice(strPrice.substring(strPrice.lastIndexOf("：")+1).trim());

			String strVersion=liNode.elementAt(2).toPlainTextString();
			app.setVersion(strVersion.substring(strVersion.lastIndexOf("：")+1).trim());
			
			app.setAppicon(appicon);
			
			app.setApkurl(apkurl);
			
			String strFilesize=liNode.elementAt(3).toPlainTextString();
			app.setFilesize(strFilesize.substring(strFilesize.lastIndexOf("：")+1).trim());
			
			String strDeveloper=liNode.elementAt(4).toPlainTextString();
			app.setDeveloper(strDeveloper.substring(strDeveloper.lastIndexOf("：")+1).trim());

			String strApptype=liNode.elementAt(5).toPlainTextString();
			app.setApptype(strApptype.substring(strApptype.lastIndexOf("：")+1).trim());

			//日期格式要转换
			String strUpdatetime=liNode.elementAt(6).toPlainTextString();
			String datatime=strUpdatetime.substring(strUpdatetime.lastIndexOf("：")+1).trim();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(datatime);
			System.out.println("date="+date);
			app.setUpdatetime(date);
			
			String strPlatform=liNode.elementAt(7).toPlainTextString();
			app.setPlatform(strPlatform.substring(strPlatform.lastIndexOf("：")+1).trim());
			
			
			appService.addApp(app);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return map;
		
	}
	
	/**
	 * 功能：获取图片路径
	 * 作者：yangxiangyang
	 * 时间：2016年1月11日上午8:32:20
	 */
	public static String getPicUrl(Parser parser,String id){
		parser.reset();
		String picurl="";
		try {
			TagNameFilter tagNameFilter=new TagNameFilter("div");
			HasAttributeFilter attributeFilter = new HasAttributeFilter("id", id);
			AndFilter andFilter=new AndFilter(tagNameFilter, attributeFilter);
			NodeList nodeList = parser.parse(andFilter);
			if(nodeList.size()>0){
				
				TagNode tagNode = (TagNode) nodeList.elementAt(0);
				TagNode firstChild = (TagNode)tagNode.getFirstChild();
				picurl = firstChild.getAttribute("src");
				System.out.println("picurl="+picurl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return picurl;
	}
	

}
