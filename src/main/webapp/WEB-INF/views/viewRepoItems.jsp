<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<jsp:include page="head.jsp" />
<script src="https://kit.fontawesome.com/f449710536.js"
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="container-fluid" data-ng-app="itemTracker">
		<div class="row justify-content-center p-3">
			<h3>Admin Panel</h3>
		</div>
		<div class="row justify-content-center">
			<div class="col-auto p-3" ng-if="'${repoItem.rName}'!=''">
				<h6>Added following item to the Repo</h6>
				<table id="syncFormResult"
					class="table-sm table-borderless table-responsive .w-auto">
					<tr>
						<td>Item Name :</td>
						<td>${repoItem.rName}</td>
					</tr>
					<tr>
						<td>Refrigerator Longevity :</td>
						<td>${repoItem.rFridgeDate}</td>
					</tr>
					<tr>
						<td>Pantry Longevity:</td>
						<td>${repoItem.rPantryDate}</td>
					</tr>
					<tr>
						<td>Freezer Longevity:</td>
						<td>${repoItem.rFreezeDate}</td>
					</tr>
				</table>
			</div>
			<div class="panel">
				<div class="tablecontainer p-3">
					<table data-ng-controller="RepoItemController as ctrl"
						class="table table-light table-hover table-responsive .w-auto"
						id="repo_items">
						<caption>
							<div class="row">
								<div class="col col-xs-8 text-left">
									<h3>Manage Repo Items</h3>
								</div>
								<div class="col col-xs-8 text-right">
									<button id="addRepoItem"
										onclick="window.location.href = 'addRepoItemForm';"
										class="btn btn-success" data-toggle="tooltip"
										data-placement="right" title="Add new item to repo">
										<i class="fas fa-plus"></i>
									</button>
								</div>
							</div>
						</caption>
						<thead>
							<tr class="table-info">
								<th>ID</th>
								<th>Name</th>
								<th>Fridge Longevity</th>
								<th>Freezer Longevity</th>
								<th>Pantry Longevity</th>
							</tr>
						</thead>
						<tbody>
							<tr data-ng-repeat="i in ctrl.repoItems">
								<td data-ng-bind="i.rId"></td>
								<td data-ng-bind="i.rName"></td>
								<td data-ng-bind="i.rFridgeDate"></td>
								<td data-ng-bind="i.rFreezeDate"></td>
								<td data-ng-bind="i.rPantryDate"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row justify-content-center p-3">
			<button id="backFromAsync"
				onclick="window.location.href = '../welcome';">Back</button>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.10/angular.min.js"
		type="text/javascript">
		
	</script>

	<script src="<c:url value='/static/js/app.js' />"
		type="text/javascript">
		
	</script>
	<script
		src="<c:url value='/static/js/controller/repo_item_controller.js' />"
		type="text/javascript">
		
	</script>
	<script src="<c:url value='/static/js/ui-bootstrap.min.js' />"
		type="text/javascript">
		
	</script>
	<script src="<c:url value='/static/js/service/repo_item_service.js' />"
		type="text/javascript">
		
	</script>
</html>