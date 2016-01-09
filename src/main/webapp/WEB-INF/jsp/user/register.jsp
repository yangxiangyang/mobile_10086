<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0040)https://account.xiaomi.com/pass/register -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
<title>中国移动注册</title>

<link href="<%=request.getContextPath() %>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/js/bootstrap/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/login/reset.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/login/layout.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/login/registerpwd.css">

<script type="text/javascript">
	function login(){
		$("#loginform").submit(function(e){
			e.preventDefault();
			$.post("<%=request.getContextPath()%>/user/addUser.do",$(this).serialize(),function(){
				console.log("注册完成！");
				location.href="<%=request.getContextPath()%>/user/loginPage.do";
			})
		})
	}
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
      <h4 class="title_big30">注册移动商城帐号</h4>          
    </div>  
    
    
    
    
    
    <!--  插入表单-->
	    <form class="form-horizontal" role="form" id="loginform"  style="margin-left: 70px" action="" method="post">
	   <div class="form-group" >
	      <div class="col-sm-8">
	         <input type="text" class="form-control" id="loginname"  name="loginname" value="${username}" readonly="readonly"
	            placeholder="登录名">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-8">
	         <input type="text" class="form-control" id="password" name="password"
	            placeholder="密码">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-8">
	         <input type="text" class="form-control" id="nickname" name="nickname"
	            placeholder="昵称">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-8">
	         <input type="text" class="form-control" id="phone" name="phone"
	            placeholder="手机">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-8">
	         <input type="text" class="form-control" id="mail" name="mail"
	            placeholder="邮箱">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-8">
	         <input type="hidden" class="form-control" id="socialUid" name="socialUid" value="${social_uid}"
	            placeholder="社会化ID">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-offset-2 col-sm-8">
	         <button type="submit" class="btn btn-default" onclick="login()">注册</button>
	      </div>
	   </div>
	</form>
	    
    
    
    
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