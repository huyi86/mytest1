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
		<form id="editForm" method="post">
			<input type="hidden" name="id">
			<table cellpadding="5">
				<tr>
					<td>CODE:</td>
					<td><input class="easyui-textbox" type="text" name="code"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>名称:</td>
					<td><input class="easyui-textbox" type="text" name="name"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>访问地址:</td>
					<td><input class="easyui-textbox" type="text" name="url" ></input></td>
				</tr>
				<tr>
					<td>优先级:</td>
					<td><input class="easyui-numberbox" name="sort" type="text" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>权限类型:</td>
					<td>
					<select name="type" class="easyui-combobox" data-options="required:true,panelHeight:90">
						<option value="0">菜单权限</option>
						<option value="1" checked="checked">功能权限</option>
						<option value="2">数据权限</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>父权限:</td>
					<td><select name="pid" id="parent_list" class="easyui-combotree" style="width:200px;"   
        				data-options="url:'/auth/treeList'"></select>  
        			</td>
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
			if(!$("#editForm").form("validate")){
				$.messager.alert('提示','表单未填写完整!');
				return;
			}
			$.post("/auth/edit",data,function(data){
				$.messager.alert('提示',data.msg,'',function(){
					if(data.status==200){
						//关闭窗口，刷新页面
						$("#w").window("close");
						$("#dg").datagrid("reload");
					}
				});
			});
		}
		function clearForm() {
			$('#editForm').form('clear');
		}
	</script>
</body>
</html>