<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<style>
#header {
	height:100px;
	background-color:black;
	color:white;
	text-align:center;
	padding:6px;
	
}
#nav {
	height:550px;
	width:15%;
	background-color:#ededed;
	float:left;
	padding:6px;
	line-height:50px;
}
#section {
	width:80%;
	height:550px;
	float:right;
	padding:10px;
}
#footer {
	height:5%;
	width:100%;
	background-color:black;
	color:white;
	text-align:center;
	padding:6px;
	clear:both;
}
</style>
</head>
<body >

	<div id="header">
	<h1>图书数据库</h1>
	</div>
	
	<div id="nav">
	<a href="queryByAuthor.jsp">根据作者查询图书</a><br>
	<a href="queryBytiTitle.jsp">根据书名查询图书</a><br>
	<a href="removeBook.jsp">删除图书</a>><br>
	<a href="addBook.jsp">新增图书</a><br>
	<a href="updateBook.jsp">更新图书</a><br>
	</div>
	
	<div id="section">
	<h1>欢迎</h1>
	<p>
	
	<s:form action="test" method="post">
		<s:textfield name="jiashu" label="加数" />
		<s:submit value="测试"></s:submit>
	</s:form>
	<br/>
	<s:form action="register" method="post">
        <s:textfield name="usrname" label="请输入用户名"></s:textfield>
        <s:textfield name="pwd" type="password" label="请输入密码"></s:textfield>
        <s:submit value="注册"></s:submit>
    </s:form>
    </p>
    
	</div>
	
	<div id="footer">
	<p>copyright by - 武德浩</p>
	</div>
	
    
</body>
</html>