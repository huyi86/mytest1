<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="easyui-datagrid" style="width: 100%;" id="dg"
		data-options="rownumbers:true,pagination:true,
		url:'/user/listAll',border:false,
		method:'get',toolbar:'#tb',footer:'#ft'">
		<thead>
			<tr>
				<th data-options="field:'id',width:70,align:'center'" >ID</th>
				<th data-options="field:'code',width:80,align:'center'">CODE</th>
				<th data-options="field:'name',width:80,align:'center'">姓名</th>
				<th data-options="field:'sex',width:80,align:'center'">电话</th>
				<th data-options="field:'age',width:60,align:'center'">邮箱</th>
				<th data-options="field:'createTime',width:150,align:'center',formatter:formatDate">创建时间</th>
				<th data-options="field:'lastModifiedTime',width:150,align:'center',formatter:formatDate">更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 2px 5px;">
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="#" onclick="openEditWindow()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		<a href="#" onclick="deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		<a href="#" onclick="exportUser()" class="easyui-linkbutton" iconCls="icon-cut" plain="true">导出</a>
	</div>
	<div id="w" class="easyui-window" title="编辑用户"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'/page/user-edit'," 
	style="width:400px;height:350px;padding:10px;">
        The window content.
    </div>
	<div id="w2" class="easyui-window" title="导出用户"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'/user/export'," 
	style="width:400px;height:350px;padding:10px;">
        The window content.
    </div>
	<script type="text/javascript">
		function exportUser(){
			var ref="width=450px,height=300px,left=500px,top=200px,resizable=yes,scrollbars=yes,status=yes,toolbar=no,systemmenu=no,location=no,borderSize=thin";
			window.open("${pageContext.request.contextPath}/user/export", "导出用户",ref,false);
		}
		
		function deleteUser(){
			var rows = $("#dg").datagrid("getSelections");
			if(!rows){
				$.messager.alert('提示','必须选择一行数据才能删除!');
        		return ;
			}
			var ids = new Array();
			var i = 0;
			$(rows).each(function(){
				ids[i] = this.id;
				i=i+1;
			});
			$.ajax({
				url:"/user/delete",
				contentType:"application/json",
				data:"["+ids.toString()+"]",
				success:function(info){
					$.messager.alert('提示',info.msg,'',function(){
						$("#dg").datagrid("reload");
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
			var userInfo = $("#dg").datagrid("getSelected");
			if(!userInfo){
				$.messager.alert('提示','必须选择一行数据才能编辑!');
        		return ;
			}
			$('#w').window({
				onLoad:function(){
					if(userInfo.sex)
						userInfo.sex="男";
					else if(userInfo.sex==false)
						userInfo.sex="女";
					userInfo.birthday=formatBirthday(userInfo.birthday);
					$("#editForm").form("load",userInfo);
				}
			}).window('open');
		};
		$(function(){
			 var pager = $('#dg').datagrid().datagrid('getPager');
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
			if(value)
				return new Date(value).format("yyyy-MM-dd hh:mm:ss")
				return "";
		};
	</script>
</body>
</html>