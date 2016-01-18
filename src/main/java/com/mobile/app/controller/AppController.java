package com.mobile.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mobile.app.bean.App;
import com.mobile.app.bean.AppThread;
import com.mobile.app.service.AppService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping("app")
public class AppController {
	
	@Autowired
	private AppService appService;
	
	/**
	 * 
	 * 功能：解析网页
	 * 作者：yangxiangyang
	 * 时间：2016年1月8日上午11:21:32
	 */
	/*@RequestMapping("analysisNet")
	public void analysisNet(String url,HttpServletRequest request){
		Map map = AppUtil.getAppMsg(url);
		
		System.out.println(map);
		App app=new App();
		app.setAppname((String) map.get("appname"));
		
//		List list= map.get("list");
		
		
	}*/
	
	@RequestMapping("analysisPage")
	public String analysisPage(){
		return "app/analysisPage";
	}
	
	@RequestMapping("analysisAllNet")
	public void analysisAllNet(){
		
		//获取总页数
		//计算每个线程的页数
		//计算启动的数量
		//启动线程
		
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
				AppThread appthread=new AppThread("线程"+i,(i*15+1),appService);
				appthread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("createHtml")
	public void createHtml(HttpServletRequest request){
		try {
			Configuration configuration=new Configuration();
			//加载flt模板的路径
			configuration.setClassForTemplateLoading(this.getClass(), "../../../../");
			
			//获取模板文件
			Template template = configuration.getTemplate("freemakerhtml.ftl");
			
			//获取根路径
			String realPath = request.getSession().getServletContext().getRealPath("/html");
			
			Map map=new HashMap();
			FileWriterWithEncoding out=null;
			//获取数据
			List<App> allApp = appService.findAllApp(null);
			for (App app : allApp) {
				map.put("app", app);
				String netname = app.getAppname().replaceAll("/", "-");
				out=new FileWriterWithEncoding(new File(realPath+"/"+netname+".html"), "UTF-8");
				template.process(map, out);
			}
			
			System.out.println("静态网页完成！");
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (TemplateException e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping("findAppTable")
	public String findAppTable(){
		List<App> allApp = appService.findAllApp(null);
		return "app/appTable";
		
	}
	
	
	@RequestMapping("tablelist")
	@ResponseBody
	public void tablelist(HttpServletRequest request,HttpServletResponse response, String draw,String start ,String length) throws Exception{

		    //获取请求次数
		    //数据起始位置
		    //数据长度

		    //总记录数
		    String recordsTotal = "0";
		    
		    Integer appCount = appService.appCount();
		    recordsTotal=appCount.toString();

		    //过滤后记录数
		    String recordsFiltered = "";

		    //定义列名
		    String[] cols = {"id","appname","version","appicon","apkurl","description","filesize","updatetime","developer","apptype","price"};
		    //获取客户端需要那一列排序
		    String orderColumn = "0";
		    orderColumn = request.getParameter("order[0][column]");
		    orderColumn = cols[Integer.parseInt(orderColumn)];
		    //获取排序方式 默认为asc
		    String orderDir = "asc";
		    orderDir = request.getParameter("order[0"
		    		+ "][dir]");

		    Map  map=new HashMap();
		    if(start.equals("0")){
		    	start="1";
		    }
		    map.put("start", Integer.parseInt(start));
		    map.put("length", Integer.parseInt(length));
		    
		    List<App> apps = appService.findAllApp(map);
		    Map<Object, Object> info = new HashMap<Object, Object>();
		    info.put("data", apps);
		    info.put("recordsTotal",recordsTotal);
		    info.put("recordsFiltered","");
		    info.put("draw",draw);
		    String json = new Gson().toJson(info);
//		    System.out.println("json="+json);
		    response.getWriter().write(json);
		
	}

	
}
