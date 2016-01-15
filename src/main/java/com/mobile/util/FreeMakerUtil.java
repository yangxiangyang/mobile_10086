package com.mobile.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMakerUtil {
	
	public void createHtml() throws Exception{
		Configuration configuration=new Configuration();
		//加载flt模板的路径
		configuration.setClassForTemplateLoading(this.getClass(), "../../../");
		
		//获取模板文件
		Template template = configuration.getTemplate("freemakerhtml.ftl");
		
		Map map=new HashMap();
		map.put("title", "ydmm-10086");
		
		FileWriterWithEncoding out=new FileWriterWithEncoding(new File("E:\\AppFiles\\html\\freemaker.html"), "UTF-8");
		
		template.process(map, out);
		
	}
	
	public static void main(String[] args) throws Exception {
		FreeMakerUtil util=new FreeMakerUtil();
		util.createHtml();
		System.out.println("静态页面完成！");
		
		
	}
	

}
