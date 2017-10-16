<%@page import="java.util.List"%>
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
	<a href="queryByTitle.jsp">&nbsp删除图书</a>><br>
	<a href="selectAuthor.jsp">&nbsp新增图书</a><br>
	<a href="queryByTitle.jsp">&nbsp更新图书</a><br>
	</div>
	
	<div id="section">
		<br>
		<h3>您可以在每个表格的第二行修改一些数据，修改完成后点击"更新"按钮即可更新</h3>
		<br>
	<form action="update" method="post">
	
	<table border="1" align="center" width="50%" class="tablelist" height="40px" align="center">
		<tr bgcolor="#d3d3d3">
		<th>&nbsp</th>
		<th>ISBN</th>
		<th>Title</th>
		<th>AuthorID</th>
		<th>Publisher</Publisher>
		<th>PublishDate</th>
		<th>Price</th>
		</tr>
		<c:forEach items="${bookList}" var="book">
		
		<tr>
		<td>原值</td>
		<td>${book.ISBN}</td>
		<td>${book.title}</td>
		<td>${book.authorID}</td>
		<td>${book.publisher}</td>
		<td>${book.publishDate}</td>
		<td>${book.price}</td>
		</tr>
		
		<tr>
		<td>新值</td>
		<td><input type="hidden" name="book.ISBN" value="${book.ISBN}" />此项不可更改</td>
		<td><input type="text" name="book.title" value="${book.title}" /></td>
		<td><input type="text" name="book.authorID" value="${book.authorID}" /></td>
		<td><input type="text" name="book.publisher" value="${book.publisher }" /></td>
		<td><input type="text" name="book.publishDate" value="${book.publishDate }" /></td>
		<td><input type="text" name="book.price" value="${book.price}" /></td>
		</tr>
		
		</c:forEach>
	
	</table>
<br>
<br>
<table border="1" align="center" width="50%" class="tablelist" height="40px" align="center">
		<tr bgcolor="#d3d3d3">
		<tr>
		<th>&nbsp</th>
		<th>AuthorID</th>
		<th>Name</th>
		<th>Age</th>
		<th>Country</Country>
	</tr>
		<c:forEach items="${authorList}" var="author">
			<tr>
				<td>原值</td>
				<td>${author.authorID}</td>
				<td>${author.name}</td>
				<td>${author.age}</td>
				<td>${author.country}</td>
			</tr>
			
			<tr>
			<td>新值</td>
			<td><input type="text" name="author.authorID" value="${author.authorID }" /></td>
			<td><input type="text" name="author.name" value="${author.name }" /></td>
			<td><input type="text" name="author.age" value="${author.age }" /></td>
			<td><input type="text" name="author.country" value="${author.country }" /></td>
			</tr>
		</c:forEach>
		
		<tr>
		<td></td>
		</tr>
	
	</table>
	
	<h3><input type="submit" value="更新" /></h3>
</form>

	
	</div>
	
	<div id="footer">
	<p>copyright by - 武德浩</p>
	</div>
	
</body>
</html>