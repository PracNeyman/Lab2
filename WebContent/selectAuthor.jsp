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
		<h2>请先输入要增加的图书的作者姓名，确定该作者在不在库中</h2>
		<s:form action="queryAuthorByName" method="post" align="center">
			<s:textfield name="author.name" label="作者名字" align="center" />
			<s:submit value="通过作者名字查询" align="center"></s:submit>
		</s:form>
		<br>
		<br>
		<h3>以下为您查找的作者信息，如果和您要添加的图书作者信息相符，请选择该作者；如果查找后为空或者不相符，请增加您想要的作者</h3>
		
	<table border="1" align="center" width="50%" class="tablelist" height="40px">
		<tr bgcolor="#d3d3d3">
			<th>AuthorID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Country</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${authorList}" var="author">
			<tr>
				<td>${author.authorID}</td>
				<td>${author.name}</td>
				<td>${author.age}</td>
				<td>${author.country}</td>
				<td><a href="addBook.jsp?authorID=${author.authorID}">选择该作者</a></td>
			</tr>
		</c:forEach>
		
	</table>
	<%List<AuthorDTO> authorList=(List)ActionContext.getContext().get("authorList"); %>
	<%if(authorList.isEmpty()){out.print("没有该作者");} %>
	<br>
	<br>
	<h3>如果查询过后，上表中出现了您想要的作者，请选择该作者，并忽略下表&#10如果没有您想要的作者，请在下列表中填入该作者并点击添加按钮</h3>
	<form action="addAuthor" method="post">
		<table border="1" align="center" width="50%" class="tablelist" height="40px">
		<tr bgcolor="#d3d3d3" height="60px">
			<th>AuthorID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Country</Country>
		</tr>
		<tr height="45px">
			<td><input type="text" name="author.authorID" value="" /></td>
			<td><input type="text" name="author.name" value="" /></td>
			<td><input type="text" name="author.age" value="" /></td>
			<td><input type="text" name="author.country" value="" /></td>
		</tr>
		
	</table>
	<br>
	<input type="submit" value="添加该作者" />
	</form>
	

	
	</div>
	
	<div id="footer">
	<p>copyright by - 武德浩</p>
	</div>
	
</body>
</html>