package com.mobile.test;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;

public class TestThread {
	
	public static void main(String[] args) {
		
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
				HtmlThread htmlThread=new HtmlThread("�߳�"+i,(i*15+1));
				htmlThread.start();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
