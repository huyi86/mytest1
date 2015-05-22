<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="easyui-datagrid" style="width: 100%;" id="dg_user_role"
		data-options="rownumbers:true,pagination:true,
		url:'/user/listAll',border:false,
		method:'get',toolbar:'#tb_user_role',footer:'#ft'">
		<thead>
			<tr>
				<th data-options="field:'id',width:70,align:'center'" >ID</th>
				<th data-options="field:'name',width:80,align:'center'">姓名</th>
				<th data-options="field:'code',width:200,align:'center'">CODE</th>
				<th data-options="field:'createTime',width:150,align:'center',formatter:formatDate">创建时间</th>
				<th data-options="field:'lastModifiedTime',width:150,align:'center',formatter:formatDate">更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb_user_role" style="padding: 2px 5px;">
		姓名：<input name="name" class="easyui-textbox" style="width: 110px">
		CODE：<input name="code" class="easyui-textbox" style="width: 110px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		<a href="#" onclick="openEditWindow()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">用户授权</a>
	</div>
	<div id="user_role_edit" class="easyui-window" title="编辑用户"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'/page/user-role-edit'," 
	style="width:400px;height:350px;padding:10px;">
        The window content.
    </div>
	<script type="text/javascript">
		function openEditWindow(){
			var userInfo = $("#dg_user_role").datagrid("getSelected");
			if(!userInfo){
				$.messager.alert('提示','必须选择一行数据才能编辑!');
        		return ;
			}
			$('#user_role_edit').window({
				onLoad:function(){
					$("#editForm_user_role").form("load",userInfo);
				}
			}).window('open');
		};
		$(function(){
			 var pager = $('#dg_user_role').datagrid().datagrid('getPager');
			 pager.pagination({
					buttons:[{
						iconCls:'icon-search',
						handler:function(){
							alert('search');
						}
					},{
						iconCls:'icon-add',
						handler:function(){
							alert('add');
						}
					},{
						iconCls:'icon-edit',
						handler:function(){
							alert('edit');
						}
					}]
				});	

		});
		function formatDate(value,row){
			return new Date(value).format("yyyy-MM-dd hh:mm:ss")
		};
	</script>
</body>
</html>