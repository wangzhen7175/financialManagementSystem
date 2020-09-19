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
	$('#datagrid_income').datagrid({
        url:'<%=basePath%>/paymentsController/query.do?paymenttype=1',
        singleSelect:true,
        height:$('#tabs').height() - 50 - $('#btn').height(),
        pagination:true,
        pageSize:20,
        pageList:[10,20,30,50],
        columns:[[
            {field:'id',title:'id',hidden:true},
            {field:'name',title:'收入名称',width:250},
            {field:'value',title:'收入金额',width:100},
            {field:'unitname',title:'单位',width:90},
            {field:'typename',title:'类别',width:100},
            {field:'day',title:'收入日期',width:80},
            {field:'crttime',title:'记录时间',width:130},
            {field:'descript',title:'描述',width:250}
        ]],
        toolbar: [{
            text:'添加',
            iconCls: 'icon-add',
            handler: function(){
                $('#window').window({
                    title:'添加',
                    closed:false
                });
                $('#window').window('refresh', '<%=basePath%>/jsp/addincome.jsp');
            }
        },'-',{
            text:'编辑',
            iconCls: 'icon-edit',
            handler: function(){
            	var select = $('#datagrid_income').datagrid('getSelected');
                if (select == null){
                    $.messager.alert("Info","请选择记录！","Info");
                }
                else{
                    $.ajax({
                        type: 'POST',   
                        url: '<%=basePath%>/paymentsController/preUpdatePayments.do',
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
            	var select = $('#datagrid_income').datagrid('getSelected');
                if (select == null){
                    $.messager.alert("Info","请选择记录！","Info");
                    return ;
                }
                else{
                	$.messager.confirm('提示信息', '确认删除吗?', function(r){
                		if (r){
                			$.ajax({
                                type: 'POST',   
                                url: '<%=basePath%>/paymentsController/deletePayments.do',
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
                                    $('#datagrid_income').datagrid('load');
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
    $('#datagrid_income').datagrid('load', 
    		{name:'%'+$('#incomename').val()+'%',
             type:$('#incometype').combobox('getValue'),
             day:$('#incomeday').datebox('getValue').replace(/\-/g, '')}
             );
}
	
</script>
<form id="searchform2" method="post">
    <span>收入名称：</span><input id="incomename" name="incomename" />
    <span>收入类型：</span><input id="incometype" name="incometype" class="easyui-combobox"
                        data-options="
                        valueField:'code',
                        textField:'codename',
                        url:'<%=basePath%>/commonController/listDatadictCata.do?catalog=income'"/>
    <span>收入日期：</span><input id="incomeday" type="text" class="easyui-datebox" />
    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" 
                  onclick="javascript:onSubmit2()">查询</a>
</form>
<div>
    <table id="datagrid_income"></table>
</div>
