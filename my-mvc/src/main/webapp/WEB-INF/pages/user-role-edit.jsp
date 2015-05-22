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
		<form id="editForm_user_role" method="post">
			<input type="hidden" name="id">
			<table cellpadding="5">
				<tr>
					<td>用户编号:</td>
					<td><input class="easyui-textbox" type="text" name="code" readonly="readonly"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input class="easyui-textbox" type="text" name="name" readonly="readonly"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>角色:</td>
					<td>
        				 <input class="easyui-combobox" name="roleIds" data-options="
				                url: '/role/getAll',
				                method: 'get',
				                valueField: 'id',
				                textField: 'name',
				                panelWidth: 350,
				                panelHeight: 'auto',
				                formatter: formatItem,
				                multiple:true,
				                multiline:true
				            " style="width:180px;height:60px">
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
	 function formatItem(row){
         var s = '<span style="font-weight:bold">' + row.name + '</span><br/>' +
                 '<span style="color:#888">' + row.remark + '</span>';
         return s;
     }
		function submitForm() {
			if(!$("#editForm_user_role").form("validate")){
				$.messager.alert('提示','表单未填写完整!');
				return;
			}
			var data = $('#editForm_user_role').serialize();
			$.post("/user/addRole",data,function(data){
				$.messager.alert('提示',data.msg,'',function(){
					if(data.status==200){
						//关闭窗口，刷新页面
						$("#user_role_edit").window("close");
						$("#dg_user_role").datagrid("reload");
					}
				});
			});
		}
		function clearForm() {
			$('#editForm_user_role').form('clear');
		}
	</script>
</body>
</html>