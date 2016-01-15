<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0019)http://mm.10086.cn/ -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试页面</title>
<meta name="keywords" content="">
<meta name="description" content="资讯频道">
</head>
<body>
	<h2>测试控制台</h2>

	测试第三方登陆SUCCESS
	<p/>
	<a href="<%=request.getContextPath()%>/app/analysisAllNet.do">分析全部软件</a>
	<p/>
	<a href="<%=request.getContextPath()%>/app/createHtml.do">创建静态页面</a>
	<p/>
	<a href="<%=request.getContextPath()%>/app/findAppTable.do">datatable</a>

</body>
</html>