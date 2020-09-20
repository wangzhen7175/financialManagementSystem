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
	$('#datagrid_buget').datagrid({
        url:'./query.do',
        singleSelect:true,
        height:$('#tabs').height() - 50 - $('#btn').height(),
        pagination:true,
        pageSize:20,
        pageList:[10,20,30,50],
        columns:[[
            {field:'id',title:'id',hidden:true},
            {field:'month',title:'月份',width:250},
            {field:'value',title:'收入金额',width:100},
            {field:'unitname',title:'单位',width:100}
        ]],
        toolbar: [{
            text:'添加',
            iconCls: 'icon-add',
            handler: function(){
                $('#window').window({
                    title:'添加',
                    closed:false
                });
                $('#window').window('refresh', './addbuget.jsp');
            }
        },'-',{
            text:'编辑',
            iconCls: 'icon-edit',
            handler: function(){
            	var select = $('#datagrid_buget').datagrid('getSelected');
                if (select == null){
                    $.messager.alert("Info","请选择记录！","Info");
                }
                else{
                    $.ajax({
                        type: 'POST',   
                        url: './preUpdateBuget.do',
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
            	var select = $('#datagrid_buget').datagrid('getSelected');
                if (select == null){
                    $.messager.alert("Info","请选择记录！","Info");
                    return ;
                }
                else{
                	$.messager.confirm('提示信息', '确认删除吗?', function(r){
                		if (r){
                			$.ajax({
                                type: 'POST',   
                                url: './deleteBuget.do',
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
                                    $('#datagrid_buget').datagrid('load');
                                  }
                            });
                		}
                	});
                }
            }
        }],
        onLoadSuccess:function(data){
        }
    });
});
function onSubmit2()
{
    $('#datagrid_buget').datagrid('load', 
    		{month:$('#month').val()}
             );
}
	
</script>
<form id="searchform2" method="post">
    <span>预算月份：</span><input id="month" name="month" />
    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" 
                  onclick="javascript:onSubmit2()">查询</a>
</form>
<div>
    <table id="datagrid_buget"></table>
</div>
