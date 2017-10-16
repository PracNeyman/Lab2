<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryByAuthor</title>
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
	<br>
	<h2>请输入新增的图书信息，其中AuthorID为之前您选中或添加的作者ID，无须更改</h2>
	<br>
	<form action="addBook" method="post">
		<table border="1" align="center" width="35%" class="tablelist" height="35px">
		<tr>
			<th>ISBN</th>
			<th>Title</th>
			<th>AuthorID</th>
			<th>Publisher</th>
			<th>PublishDate</th>
			<th>Price</th>
		</tr>
		<tr>
			<td><input type="text" name="book.ISBN" value="" /></td>
			<td><input type="text" name="book.title" value="" /></td>
			<%if(ActionContext.getContext().get("authorID")==null) { %>
			<td><input type="hidden" name="book.authorID" value="<%=request.getParameter("authorID") %>" /><%=request.getParameter("authorID") %></td>
			<%}else{ %>
			<td><input type="hidden" name="book.authorID" value="${authorID}" />${authorID }</td>
			<%} %>
			<td><input type="text" name="book.publisher" value="" /></td>
			<td><input type="text" name="book.publishDate" value="" /></td>
			<td><input type="text" name="book.price" value="" /></td>
		</tr>
		
		</table>
		<br>
		<input type="submit" value="确认无误，添加" align="center" />
	</form>
	</div>
	<div id="footer">
	<p>copyright by - 武德浩</p>
	</div>
	
</body>
</html>