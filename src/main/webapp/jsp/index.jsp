<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
    
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>常君同学毕业设计</title>
<link rel="stylesheet" type="text/css"
    href="./res/css/easyui/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./res/css/easyui/icon.css">

<script type="text/javascript" src="./res/js/jquery.min.js"></script>
 <script type="text/javascript" src="./res/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="./res/ujs/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./res/js/highcharts.js"></script>
<script type="text/javascript" src="./res/js/highcharts-more.js"></script>
<script type="text/javascript" src="./res/js/charts.js"></script>

<script>
$(function(){
    alert("执行");
	$('#tabs').tabs('add',{
		title:'首页',
		tools:[{
	        iconCls:'icon-mini-refresh',
	        handler:function(){
	        	var tab = $('#tabs').tabs('getSelected');
	        	tab.panel('refresh', 'main.jsp');
	        }
	    }],
        href:'main.jsp',
        closable:false
	});
	$('.easyui-tree').tree({
        onClick:function(node){
        	if (node.attributes.url != '' && node.attributes.url != null)
            {
                if ($('.easyui-tabs').tabs('exists', node.text))
                {
                    $('.easyui-tabs').tabs('select', node.text);
                }
                else
                {
                    $('.easyui-tabs').tabs('add',{   
                        title:node.text,   
                        href:node.attributes.url,   
                        closable:true  
                    });
                }
            }
        }
    });
});
</script>
</head>
<body class="easyui-layout">
		
    <div data-options="region:'north',noheader:true,split:false" style="height:70px;background-color:rgb(139,235,218)">
          欢迎您&nbsp;&nbsp;${sessionScope.sessionUser}&nbsp;&nbsp;|&nbsp;&nbsp;
          <a href="./toPwd.do" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="./quit.do" target="_parent">退出系统</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="https://weibo.com/u/5291918666?from=myfollow_all&is_all=1" target="_top">联系我们</a>	
    
        <h1>家庭财务管理系统</h1>
    </div>
    	
    
    <div data-options="region:'south',noheader:true,split:false" style="height:50px;"></div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
        <div class="easyui-accordion"  border="false" id='menu'>
            ${menus}
         </div>
    </div>
    <div data-options="region:'center'," style="padding:1px;">
        <div id='tabs' class="easyui-tabs" data-options="fit:true,border:false">
        </div>
    </div>
    <div id="window" class="easyui-window" data-options="shadow:false,modal:true,closed:true,collapsible:false,minimizable:false" style="width:530px;padding:10px;top:200%;">
        
    </div>
</body>
</html>