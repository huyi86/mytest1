<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css"
	href="/js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/js/themes/icon.css">
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		$("#myTree1").tree({
			onClick:function(note){
				addPanel(note);
			}
		});
		$("#myTree2").tree({
			onClick:function(note){
				addPanel(note);
			}
		});
	});
	 function addPanel(note){
		 if(note.id==null){
			 if( $('#myTabs').tabs('exists',note.text)){
				 $('#myTabs').tabs('select',note.text)
			 }else{
				 $('#myTabs').tabs('add',{
		             title: note.text,
		             href:'/page/'+note.url,
		             closable: true
		         });
			 }
		 }
     }
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" align="center"
		style="height: 60px; background: #B3DFDA; padding: 10px; font-size: 30px;">欢迎光临管理系统</div>
	<div data-options="region:'west',split:true,title:'用户管理菜单'"
		style="width: 250px; height: 800px; padding: 3px;">
		<div class="easyui-accordion" data-options="border:false">
			<div title="系统管理" style="padding: 10px;">
				<ul class="easyui-tree" id="myTree1"
					data-options="url:'/js/tree_userMenu.json',method:'get',animate:true"></ul>
			</div>
			<div title="权限管理" style="padding: 10px;">
				<ul class="easyui-tree" id="myTree2"
					data-options="url:'/js/tree_sysMenu.json',method:'get',animate:true"></ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center'" >
		<div class="easyui-tabs" style="width: 100%;" id="myTabs" data-options="border:false" style="padding: 10px">
		</div>
	</div>
</body>
</html>