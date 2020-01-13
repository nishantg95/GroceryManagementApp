<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<jsp:include page="head.jsp" />  
</head>

<body data-ng-app="itemTracker">
	<div class="jumbotron text-center jumbotron-fluid" data-ng-controller="ItemController as ctrl" >
		<div class="container" data-ng-model="ctrl.items">
			<h2>Welcome Back, Nishant!!</h2>
			<p>You have <span>{{ctrl.items.length}}</span> ingredients in your inventory.</p>
		</div>
	</div>
		<div class = "container-fluid">
	<div class="bar">
		<p>
			Overall health:
			<progress max="100" value="77"></progress>
		</p>
	</div>
	<div class="row justify-content-center p-3">
		<button id="syncPageLink" onclick="window.location.href = 'repo/viewRepoItems';">Admin Panel</button>
		<button id="asyncPageLink" onclick="window.location.href = 'inventory';">View Inventory</button>
	</div>
	</div>
	<!-- Script includes for js files -->
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.10/angular.min.js"
		type="text/javascript"></script>
	<script src="<c:url value='/static/js/app.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/static/js/service/item_service.js' />"
		type="text/javascript"></script>
	<script
		src="<c:url value='/static/js/controller/item_controller.js' />"
		type="text/javascript"></script>
			<script
		src="<c:url value='/static/js/ui-bootstrap.min.js' />"
		type="text/javascript">
	</script>
		<script src="<c:url value='/static/js/service/repo_item_service.js' />"
		type="text/javascript">
	</script>
	
</body>
</html>