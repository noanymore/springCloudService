<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
$.ajax({
url:"res.jsp",
method:"post",
dataType:"json",
success:function(res){
$.each(res,function(i,n){
$("#tbody").append("<tr><td>" + (i+1) + ":" + n + "</td></tr>")
});
}
})
});
</script>
  </head>
  
  <body>
    <table border="1">
     <thead>
     <tr>
     <th>标题</th>
     </tr>
     </thead>
     <tbody id="tbody"></tbody>
    </table>
  </body>
</html>

其次：res.jsp
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
java.util.List<String> list = new java.util.ArrayList<String>();
list.add("\"金日成\"");
list.add("\"金正日\"");
list.add("\"金正恩\"");
out.print(list);
 %>