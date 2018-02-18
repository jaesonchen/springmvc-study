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
    <title>My JSP 'upload.jsp' starting page</title>
  </head>
  
  <body>
	<form action="<%=path%>/upload/submit" method="post" enctype="multipart/form-data">
	    <input type="file" name="file" />
	    <input type="submit" value="submit" />
	</form>

  </body>
</html>
