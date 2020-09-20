<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<script>
$(function(){
	$('#datagrid_payincome').datagrid({
		title:'收支表',
        url:'./payincome.do',
        border:false,
        height:105,
        columns:[[
                  {field:'type',title:'类型',width:250,
                	  styler: function(value,row,index){
                		  if (index == 0)
                              return 'color:red;';
                          else
                              return 'color:blue;';	  
                      }
                  },
                  {field:'week',title:'本周',width:250,
                      styler: function(value,row,index){
                          if (index == 0)
                              return 'color:red;';
                          else
                              return 'color:blue;';   
                      }},
                  {field:'month',title:'本月',width:250,
                          styler: function(value,row,index){
                              if (index == 0)
                                  return 'color:red;';
                              else
                                  return 'color:blue;';   
                          }},
                  {field:'year',title:'本年',width:250,
                              styler: function(value,row,index){
                                  if (index == 0)
                                      return 'color:red;';
                                  else
                                      return 'color:blue;';   
                              }}
          ]]
    });
	$.ajax({
        type: 'POST',   
        url: './getMonthSumByType.do',
        data: '',
        dataType:'text',
        success: function(msg){
        	var temp = $.parseJSON(msg); 
        	createChart('zcqxt','本月支出去向图',eval(temp.data1),'pie',350,390,'元');
        	createChart('zcqst','本月支出趋势图',eval(temp.data2),'line',360,950,'元');
        }
    });
});
</script>
<div id="layout" class="easyui-layout" data-options="fit:true">
   <div data-options="region:'north',split:true,border:false" style="height:110px">
       <table id="datagrid_payincome"></table>
   </div>
   <div data-options="region:'west',split:true,border:false" style="width:400px">
       <div id="zcqxt"></div>
   </div>
   <div data-options="region:'center',border:false">
       <div id="zcqst"></div>
   </div>
</div>
