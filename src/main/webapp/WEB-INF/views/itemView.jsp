<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
	<jsp:include page="head.jsp" />  
</head>


<body>
	<h3>Submitted Item Information</h3>
	<div id="inner">
		<table id="syncFormResult">
			<tr>
				<td>Name :</td>
				<td>${item.name}</td>
			</tr>
			<tr>
				<td>ID :</td>
				<td>${item.id}</td>
			</tr>
			<tr>
				<td>Shelf Life:</td>
				<td>${item.shelfLife}</td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<div id="center_button">
		<button id="backFromAsync" onclick="window.location.href = 'addItemForm';">Back</button>
	</div>
</body>
</html>