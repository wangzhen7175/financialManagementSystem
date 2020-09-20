<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<script>
$(function(){
	$('#dg').datagrid({
        url:'./listDatadict.do',
        singleSelect:true,
        columns:[[
            {field:'id',title:'id',hidden:true,width:300},
            {field:'catalog',title:'类别',width:300},
            {field:'code',title:'代码',width:300},
            {field:'codename',title:'代码名称',width:300}
        ]],
        toolbar: [{
        	text:'添加',
            iconCls: 'icon-add',
            handler: function(){
            	$('#window').window({
            		title:'添加',
            		closed:false
            	});
            	$('#window').window('refresh', './adddatadict.jsp');
            }
        },'-',{
        	text:'编辑',
            iconCls: 'icon-edit',
            handler: function(){
            	var select = $('#dg').datagrid('getSelected');
                if (select == null){
                    $.messager.alert("Info","请选择记录！","Info");
                }
                else{
                    $.ajax({
                        type: 'POST',   
                        url: './preUpdateDatadictData.do',
                        data: 'id='+select.id,
                        dataType:'text',
                        success: function(msg){
                        	$('#window').window({
                                title:'编辑',
                                closed:false,
                                content:msg
                            });
                        }
                    });
                }
            }
        },'-',{
        	text:'删除',
            iconCls: 'icon-remove',
            handler: function(){
            	var select = $('#dg').datagrid('getSelected');
            	if (select == null){
            		$.messager.alert("Info","请选择记录！","Info");
            	}
            	else{
            		$.ajax({
            			type: 'POST',   
                        url: './deleteDatadictData.do',
                        data: 'id='+select.id,
                        dataType:'text',
                        success: function(msg){
                            var temp = $.parseJSON(msg); 
                            if (temp.success) {
                                $.messager.alert('删除成功！',temp.msg,'Info');
                            }
                            else{
                                $.messager.alert('删除失败',temp.msg,'Info');
                            }
                            $('#dg').datagrid('load');
                          }
            		});
            	}
            }
        }],
        onLoadSuccess:function(data){
        }
    });
});
	
</script>
<div>
    <table id="dg"></table>
</div>
