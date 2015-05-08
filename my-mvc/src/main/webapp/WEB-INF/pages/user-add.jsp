<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
</head>
<body>

	<div style="padding: 10px 60px 20px 60px">
		<form id="ff" method="post">
			<table cellpadding="5">
				<tr>
					<td>用户编号:</td>
					<td><input class="easyui-textbox" type="text" name="code"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input class="easyui-textbox" type="password" name="password"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input class="easyui-textbox" type="text" name="name"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>电话:</td>
					<td><input class="easyui-textbox" name="mobile" type="text" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input class="easyui-textbox" name="email" type="text" data-options="required:true"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: left; padding: 10px">&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitForm()">提交</a> &nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="clearForm()">清空</a>
		</div>
	</div>
	<script>
		function submitForm() {
			if(!$("#ff").form("validate")){
				$.messager.alert('提示','表单未填写完整!');
				return;
			}
			$.post("/user/save",$('#ff').serialize(),function(data){
				$.messager.alert('提示',data.msg);
			});
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>
</html>