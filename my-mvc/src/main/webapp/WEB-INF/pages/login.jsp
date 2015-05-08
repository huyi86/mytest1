<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/themes/icon.css">
	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
</head>
<body> 
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="New Topic" style="width:400px">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" action="/user/login">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input class="easyui-validatebox textbox" type="text" name="code" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-validatebox textbox" type="password" name="password" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
	    	<a href="${pageContext.request.contextPath }/page/register" class="easyui-linkbutton" >去注册</a>
	    </div>
	    </div>
	   <span id="error"></span>
	</div>
	<style scoped="scoped">
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
	<script>
		function submitForm(){
			$('#ff').form('submit',{success:function(data){
				if(JSON.parse(data).status==200){
					window.location.href="/page/index";
				}else{
					$("#error").html("<font color='red'>用户名或密码错误！</font>");
				}
			}});
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>