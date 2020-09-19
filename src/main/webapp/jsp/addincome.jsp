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
        url : '<%=basePath%>/paymentsController/addPayments.do?paymenttype=1',
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
        	$('#datagrid_income').datagrid('load');
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
                <label>收入名称:</label>
                </td>
                <td>
                <input class="easyui-validatebox" id = "name" name="name" 
		        style="width: 290px;" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td>
                <label>收入金额:</label>
                </td>
                <td>
                <input class="easyui-validatebox" type="text" id="value" name="value" 
                    style="width: 290px;" data-options="required:true">
                <input class="easyui-combobox" id = "unit" name="unit" 
                data-options="required:true,
                    valueField:'code',
                    textField:'codename',
                    width:80,
                    url:'<%=basePath%>/commonController/listDatadictCata.do?catalog=currency'
                " />
                </td>
            </tr>
            <tr>
                <td>
                <label>收入类别:</label>
                </td>
                <td>
                <input class="easyui-combobox" id = "type" name="type" 
                data-options="required:true,
                    valueField:'code',
                    textField:'codename',
                    url:'<%=basePath%>/commonController/listDatadictCata.do?catalog=income'
                " />
                </td>
            </tr>
            <tr>
                <td>
                <label>收入日期:</label>
                </td>
                <td>
                <input class="easyui-datebox" id = "day" name="day" />
                </td>
            </tr>
            <tr>
                <td>
                <label>描述:</label>
                </td>
                <td>
                <textarea id="descript" name="descript" style="height: 70px; width: 290px" ></textarea>
                </td>
            </tr>
        </table>
</form>
</div>
<div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancel()">取消</a>
</div>
