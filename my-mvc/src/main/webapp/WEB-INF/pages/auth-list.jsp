<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="easyui-datagrid" style="width: 100%;" id="dg_auth"
		data-options="rownumbers:true,pagination:true,
		url:'/auth/listAll',border:false,
		method:'get',toolbar:'#tb_auth',footer:'#ft'">
		<thead>
			<tr>
				<th data-options="field:'id',width:70,align:'center'" >ID</th>
				<th data-options="field:'code',width:70,align:'center'" >CODE</th>
				<th data-options="field:'name',width:80,align:'center'">NAME</th>
				<th data-options="field:'url',width:80,align:'center'">菜单地址</th>
				<th data-options="field:'sort',width:80,align:'center'">优先级</th>
				<th data-options="field:'pid',width:60,align:'center'">父权限</th>
				<th data-options="field:'type',width:60,align:'center'">权限类型</th>
				<th data-options="field:'remark',width:60,align:'center'">权限描述</th>
			</tr>
		</thead>
	</table>
	<div id="tb_auth" style="padding: 2px 5px;">
		<a href="#" onclick="openAddWindow()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="#" onclick="openEditWindow()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		<a href="#" onclick="deleteAuth()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div id="edit_auth" class="easyui-window" title="编辑权限"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'/page/auth-edit'," 
	style="width:400px;height:350px;padding:10px;">
        The window content.
    </div>
	<div id="add_auth" class="easyui-window" title="添加权限"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'/page/auth-add'," 
	style="width:400px;height:350px;padding:10px;">
        The window content.
    </div>
	<script type="text/javascript">
		function deleteAuth(){
			var rows = $("#dg_auth").datagrid("getSelections");
			if(!rows){
				$.messager.alert('提示','必须选择一个权限才能删除!');
        		return ;
			}
			var ids = new Array();
			var i = 0;
			$(rows).each(function(){
				ids[i] = this.id;
				i=i+1;
			});
			$.ajax({
				url:"/auth/delete",
				contentType:"application/json",
				data:"["+ids.toString()+"]",
				success:function(info){
					$.messager.alert('提示',info.msg,'',function(){
						$("#dg_auth").datagrid("reload");
					});
				},
				type:"POST"
			});
			/* $.post("/user/delete",{"":"[1,2,3]"},function(data){
				$.messager.alert('提示',data.msg,'',function(){
					$("#dg").datagrid("reload");
				});
			}); */
		}
		function openEditWindow(){
			var authInfo = $("#dg_auth").datagrid("getSelected");
			if(!authInfo){
				$.messager.alert('提示','必须选择一行数据才能编辑!');
        		return ;
			}
			$('#edit_auth').window({
				onLoad:function(){
					$("#editForm").form("load",authInfo);
				}
			}).window('open');
		};
		function openAddWindow(){
			$('#add_auth').window('open');
		};
		$(function(){
			 var pager = $('#dg_auth').datagrid().datagrid('getPager');
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