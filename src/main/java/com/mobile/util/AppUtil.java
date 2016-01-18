package com.mobile.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;

import com.mobile.app.bean.App;
import com.mobile.app.service.AppService;
import com.mobile.comment.bean.Comment;
import com.mobile.pic.bean.Pic;

public class AppUtil {
	
	
	/**
	 * ���ܣ���ȡAPP����
	 * ���ܣ���ȡAPPͼ���APP����·��,����
	 * ���ߣ�yangxiangyang
	 * ʱ�䣺2016��1��9������11:44:49
	 * ˵����ֱ�ӵ���service���Ѷ��������ݿ�
	 * 1.���APP
	 * 2.�������
	 * 3.���ͼƬ
	 */
	public static void getAppMsg(String url,AppService appService){
		
		App app=new App();
		Comment appComment=new Comment();
		Pic pic=new Pic();
		
		try {
			//������ַ
			Parser parser=new Parser(url);
			//���ñ���
			parser.setEncoding("UTF-8");
//		��ȡӦ������
			TagNameFilter tagNameFilter=new TagNameFilter("title");
			NodeList nodeList = parser.parse(tagNameFilter);
			String titleString = nodeList.elementAt(0).toPlainTextString();
			String appname = titleString.substring(0,titleString.lastIndexOf("-"));
			System.out.println("appname="+appname);
			
			
			
			//���
			parser.reset();
			//��ȡͼ��
			tagNameFilter=new TagNameFilter("img");
			HasAttributeFilter attributeFilter = new HasAttributeFilter("id", "appicon");
			AndFilter andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList= parser.parse(andFilter);
			TagNode tagNode  = (TagNode) nodeList.elementAt(0);
			String appiconurl = tagNode.getAttribute("src");
			String appicon=appiconurl.substring(appiconurl.lastIndexOf("/")+1);
			HttpUtil.httpDownload(appiconurl, "E:\\AppFiles\\icons\\"+appicon);
			
			
			parser.reset();
			//��ȡӦ������·��  class=mj_xzdbd
			tagNameFilter=new TagNameFilter("a");
			attributeFilter = new HasAttributeFilter("class", "mj_xzdbd");
			andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList = parser.parse(andFilter);
			
			String apkurl ="";
			if(nodeList.size()>0){
				tagNode  = (TagNode) nodeList.elementAt(0);
				apkurl = tagNode.getAttribute("href");
				System.out.println("apkurl="+apkurl);
				if(apkurl!=null){
					HttpUtil.httpDownload(apkurl, "E:\\AppFiles\\apps\\"+appname+".apk");
				}
				
			}


			/**
			 * APP��ϸ��Ϣmj_info font-f-yh
			 */
			
			parser.reset();
			
			tagNameFilter=new TagNameFilter("div");
			attributeFilter = new HasAttributeFilter("class", "mj_info font-f-yh");
			andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList=parser.parse(andFilter);
			tagNode = (TagNode) nodeList.elementAt(0);
			NodeList liNode = tagNode.getChildren().elementAt(0).getChildren();
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < liNode.size(); i++) {
				String str=liNode.elementAt(i).toPlainTextString();
				String pro=str.substring(str.lastIndexOf("��")+1).trim();
			}
			

			/**
			 * app��ӵ����ݿ�
			 */
			app.setId(UUID.randomUUID().toString());
			
			//���ɶ�ά��
			CodeUtil.createQRCode(apkurl, "E:\\AppFiles\\code\\"+app.getId()+".jpg", "E:\\AppFiles\\icons\\"+appicon);

			app.setAppname(appname);
			
			String strPrice=liNode.elementAt(1).toPlainTextString();
			System.out.println("strPric="+strPrice.substring(strPrice.lastIndexOf("��")+1).trim());
			app.setPrice(strPrice.substring(strPrice.lastIndexOf("��")+1).trim());

			String strVersion=liNode.elementAt(2).toPlainTextString();
			app.setVersion(strVersion.substring(strVersion.lastIndexOf("��")+1).trim());
			
			app.setAppicon(appicon);
			
			app.setApkurl(apkurl);
			
			String strFilesize=liNode.elementAt(3).toPlainTextString();
			app.setFilesize(strFilesize.substring(strFilesize.lastIndexOf("��")+1).trim());
			
			String strDeveloper=liNode.elementAt(4).toPlainTextString();
			app.setDeveloper(strDeveloper.substring(strDeveloper.lastIndexOf("��")+1).trim());

			String strApptype=liNode.elementAt(5).toPlainTextString();
			app.setApptype(strApptype.substring(strApptype.lastIndexOf("��")+1).trim());

			//���ڸ�ʽҪת��
			String strUpdatetime=liNode.elementAt(6).toPlainTextString();
			String datatime=strUpdatetime.substring(strUpdatetime.lastIndexOf("��")+1).trim();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(datatime);
			System.out.println("date="+date);
			app.setUpdatetime(date);
			
			String strPlatform=liNode.elementAt(7).toPlainTextString();
			app.setPlatform(strPlatform.substring(strPlatform.lastIndexOf("��")+1).trim());
			
			
			appService.addApp(app);
			
			/**
			 * ��ȡ���� class= mj_pl_list_i
			 * �������
			 */
			
			//��ȡӦ������
			parser.reset();
//			commentUser	������
//			commentDate	����ʱ��
//			comment	��������
//			commentNum	����
//			appid	����Ӧ��
			
			parser.reset();
			//����
			tagNameFilter=new TagNameFilter("div");
			attributeFilter = new HasAttributeFilter("class", "mj_pl_list_i_m");
			andFilter=new AndFilter(tagNameFilter, attributeFilter);
			nodeList = parser.parse(andFilter);
			for (int i = 0; i < nodeList.size(); i++) {
				tagNode = (TagNode) nodeList.elementAt(i);
				NodeList children = tagNode.getChildren();
				
				appComment.setId(WebUtils.getRandomId());
//				commentUser	������
				String commentUser=children.elementAt(0).getChildren().elementAt(1).toPlainTextString();
				appComment.setCommentuser(commentUser);
//				comment	��������
				String comment = children.elementAt(1).toPlainTextString();
				appComment.setComment(comment);
				
//				commentDate	����ʱ��
				String commentDate = children.elementAt(2).getChildren().elementAt(1).toPlainTextString();
				appComment.setCommentdate(dateFormat.parse(commentDate));
				
				appComment.setAppid(UUID.randomUUID().toString());
				
				appService.addComment(appComment);
				
			}
			
			
			/**
			 * ��ȡͼƬ·��
			 * ���ͼƬ
			 */
			for (int i = 1; i <=3; i++) {
				parser.reset();
				tagNameFilter=new TagNameFilter("div");
				attributeFilter = new HasAttributeFilter("id", "mj_tu_"+i);
				andFilter=new AndFilter(tagNameFilter, attributeFilter);
				nodeList = parser.parse(andFilter);
				if(nodeList.size()>0){
					
					tagNode = (TagNode) nodeList.elementAt(0);
					TagNode firstChild = (TagNode)tagNode.getFirstChild();
					String strPicurl = firstChild.getAttribute("src");
					System.out.println("strPicurl="+strPicurl);
					HttpUtil.httpDownload(strPicurl, "E:\\AppFiles\\pictures\\"+strPicurl.substring(strPicurl.lastIndexOf("/")+1));
					pic.setId(UUID.randomUUID().toString());
					pic.setPicurl(strPicurl.substring(strPicurl.lastIndexOf("/")+1));
					pic.setApp(app.getId());
					appService.addPic(pic);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
