<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0040)https://account.xiaomi.com/pass/register -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
<title>中国移动登陆</title>

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/login/reset.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/login/layout.css">
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/login/registerpwd.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.8.3.js"></script>

 <link href="<%=request.getContextPath() %>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <script src="<%=request.getContextPath() %>/js/bootstrap/js/jquery-1.9.1.min.js"></script>
   <script src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
   
   <script type="text/javascript" id="bd_soc_login_boot"></script>
	<script type="text/javascript">
		(function(){
		  var t = new Date().getTime(),
		      script = document.getElementById("bd_soc_login_boot"),
		      redirect_uri = encodeURIComponent("http://127.0.0.1:8080/ydmm/user/threelogin.do"),
		      domid = "threelogin",
		      src = "http://openapi.baidu.com/social/oauth/2.0/connect/login?redirect_uri=" + redirect_uri + "&domid=" + domid + "&client_type=web&response_type=code&media_types=qqdenglu%2Cbaidu%2Csinaweibo%2Cqqweibo%2Ckaixin%2Crenren&size=-1&button_type=2&client_id=ZhE3XUdlhHePmlGdR84m8nCL&view=embedded&t=" + t;
		    script.src = src;
		})();
	</script>
  
   

	
<style>
.facebook_area{
  display:none!important;
}
</style>
</head>


<body class="zh_CN">
<div class="wrap">
<div class="layout">
  <div class="n-frame device-frame reg_frame" id="main_container">
    <div class="external_logo_area" align="center"><img src="<%=request.getContextPath()%>/js/login/mmlogo.png" style="margin: auto;"/></div>
    <div class="title-item t_c">
      <h4 class="title_big30">登陆移动商城帐号</h4>          
    </div>  
    
    <!--  插入表单-->
	    <form class="form-horizontal" role="form"  style="margin-left: 70px" action="<%=request.getContextPath()%>/user/login.do">
	   <div class="form-group" >
	      <div class="col-sm-8">
	         <input type="text" class="form-control" id="loginname"  name="loginname"
	            placeholder="登录名/手机/邮箱">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-8">
	         <input type="text" class="form-control" id="password" name="password"
	            placeholder="密码">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-offset-2 col-sm-8">
	         <button type="submit" class="btn btn-default">登陆</button>
	      </div>
	   </div>
	</form>
	    
    <!-- *********第三方登陆****** -->
   	<span id="threelogin"></span>
    
    
  </div>
</div>
</div>
<div class="n-footer">
  <div class="nf-link-area clearfix">
  <ul class="lang-select-list">
    <li><a href="javascript:void(0)" data-lang="zh_CN" class="current">简体</a>|</li>
    <li><a href="javascript:void(0)" data-lang="zh_TW">繁体</a>|</li>
    <li><a href="javascript:void(0)" data-lang="en">English</a></li>
    
  </ul>
  </div>
  <p class="nf-intro"><span>中国移动版权所有-京ICP备10046444-京公网安备1101080212535-京ICP证110507号</span></p>
</div>
<script src="<%=request.getContextPath()%>/js/login/placeholder.js"></script>

</body></html>