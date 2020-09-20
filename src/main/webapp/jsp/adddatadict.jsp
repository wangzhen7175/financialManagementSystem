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
	
});
function submit(){
	$('#myform').form('submit', {
        url : './addDatadictData.do',
        onSubmit : function() {
            
            if ($(this).form("validate")) {
                return true;
            } else {
                return false;
            }
        },
        success : function(data) {
        	var temp = $.parseJSON(data); 
        	if (temp.success) {
        		$.messager.alert('增加成功',temp.msg,'Info');
        	}
        	else{
        		$.messager.alert('添加失败',temp.msg,'Info');
        	}
        	$('#window').window('close');
        	$('#dg').datagrid('load');
        }
    });
}
function cancel() {
    $('#myform').form('clear');
    $('#window').window('close');
}	
</script>
<div>
    <form id="myform" method="post">
        <table align="center">
            <tr>
                <td>
                <label>选择类别:</label>
                </td>
                <td>
                <input class="easyui-combobox" id = "catalog" name="catalog" 
		        data-options="required:true,
		            valueField:'code',
                    textField:'codename',
		            url:'./listDatadictCata.do?catalog=root'
		        " />
                </td>
            </tr>
            <tr>
                <td>
                <label>代码:</label>
                </td>
                <td>
                <input class="easyui-validatebox" type="text" id="code" name="code" 
                    style="width: 290px;" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>
                <label>中文名称:</label>
                </td>
                <td>
                <input class="easyui-validatebox" type="text" id="codename" name="codename"
                    style="width: 290px;" data-options="required:true">
                </td>
            </tr>
        </table>
</form>
</div>
<div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancel()">取消</a>
</div>
