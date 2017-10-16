<%@page import="items.AuthorDTO"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>message</title>
<style>
#header {
	height:80px;
	background-color:black;
	color:white;
	text-align:center;
	padding:3px;
	
}
#nav {
	height:600px;
	width:15%;
	background-color:#eaebe6;
	float:left;
	padding:8px;
	line-height:50px;
}
#section {
	background-color: #ffffff;
	width:80%;
	height:590px;
	float:right;
	padding:10px;
	text-align:center;
}
#footer {
	height:6%;
	width:100%;
	background-color:black;
	color:white;
	text-align:center;
	padding:2px;
	clear:both;
}
table.tablelist{border:1px solid;border-collapse:collapse;}
</style>
</head>
<body>
<div id="header">
	<h1>图书数据库</h1>
	</div>
	
	<div id="nav">
	<a href="queryByAuthor.jsp">&nbsp根据作者查询图书</a><br>
	<a href="queryByTitle.jsp">&nbsp根据书名查询图书</a><br>
	<a href="queryByTitle.jsp">&nbsp删除图书</a><br>
	<a href="selectAuthor.jsp">&nbsp新增图书</a><br>
	<a href="queryByTitle.jsp">&nbsp更新图书</a><br>
	</div>
	
	<div id="section">
		
		
	<h2>${msg}</h2>
	
	<br>
	<br>
	<a href="welcome.html" ><input type="button" value="返回主页" align="center"/></a>
	
	</div>
	
	<div id="footer">
	<p>copyright by - 武德浩</p>
	</div>
	
</body>
</html>