<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@ include file="/js/src/inc.jsp" %>

<script type="text/javascript">
	function analysis(){
		$("#analysisform").submit(function(e){
			e.preventDefault();
			$.post("<%=request.getContextPath()%>/app/analysisNet.do",$(this).serialize(),function(){
			})
		})
	}
</script>
</head>
<body>


<div  style="margin-top: 100px">
	<form class="form-horizontal" role="form" id="analysisform" action="" method="post">
	   <div class="form-group">
	      <label for="firstname" class="col-sm-2 control-label">名字</label>
	      <div class="col-sm-7">
	         <input type="text" class="form-control" id="firstname"  name="url"
	            placeholder="请输入要解析的地址">
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-offset-2 col-sm-10">
	         <button type="submit" class="btn btn-default" onclick="analysis()">提交地址</button>
	      </div>
	   </div>
	</form>
</div>

</body>
</html>