package com.mobile.test;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;

import com.mobile.util.AppUtil;

public class HtmlThread  extends Thread{
	
	private String  threadName;
	private int startPage;
	
	public void run() {
		
		try {
			
			for (int i = startPage; i <=startPage+15; i++) {
				Parser parser=new Parser("http://mm.10086.cn/android/software/qbrj?p="+i);
				parser.setEncoding("UTF-8");
				
				TagNameFilter tagNameFilter=new TagNameFilter("a");
				HasAttributeFilter hasAttributeFilter=new HasAttributeFilter("class","autoword");
				AndFilter andFilter=new AndFilter(tagNameFilter, hasAttributeFilter);
				NodeList nodeList = parser.parse(andFilter);
				
				for (int j = 0; j < nodeList.size(); j++) {
					TagNode tagNode = (TagNode) nodeList.elementAt(j);
					System.out.println(threadName+"---起始页:"+startPage+"-----------"+tagNode.getAttribute("title")+"---"+tagNode.getAttribute("href"));
					System.out.println("APP的网页地址是："+"http://mm.10086.cn"+tagNode.getAttribute("href"));
//					AppUtil.getAppMsg("http://mm.10086.cn"+tagNode.getAttribute("href"));
					
				}
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public HtmlThread() {
		super();
	}

	public HtmlThread(String threadName) {
		super();
		this.threadName = threadName;
	}

	public HtmlThread(String threadName, int startPage) {
		super();
		this.threadName = threadName;
		this.startPage = startPage;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	

}
