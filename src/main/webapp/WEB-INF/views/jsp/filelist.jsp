<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>My JSP 'filelist.jsp' starting page</title>
  </head>
  
  <body>
	<h1><a href='<%=path%>/download?name=${fileName}'>点击下载</a></h1>
    <div style = "padding: 3px; border: solid 1px #cccccc; text-align: center" >
		<img src = '${fileUrl}'/>
    </div>
  </body>
</html>
