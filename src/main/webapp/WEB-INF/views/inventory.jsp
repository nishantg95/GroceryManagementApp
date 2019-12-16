<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!--need this for c: tag  -->
<html>

<head>
	<jsp:include page="head.jsp" />
	<script src="https://kit.fontawesome.com/f449710536.js" crossorigin="anonymous"></script>
</head>


<body ng-app="myApp" >
	<!-- Center div -->
	<div class = "container-fluid" ng-controller="ItemController as ctrl">
	
		<!-- Map page to controller -->

			<h3>Items Available</h3>
				<form ng-submit="ctrl.submit()" id="asyncForm"
					class="form-inline" >
					<!--  TODO: Spans for validation -->			
				</form>


			<!-- List view of Items -->
			<div class="panel">
				<!-- Table container div -->
				<div class="tablecontainer" >
					<table class="table table-light table-hover">
						<thead>
							<tr>
								<th>Name</th>
								<th>Shell Life</th>
								<th>Storage State</th>
								<th>Purchase Date</th>
								<th>Expiry Date</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr class="table-info">
								<!-- Name field -->
								<td>
									<input type="text" ng-model="ctrl.item.name" id="name"
										class="form-control input-sm" placeholder="Item name"  form="asyncForm" required />
								</td>
								<!-- Shell Life field -->
								<td>
									<input type="text" ng-model="ctrl.item.shelfLife" id="expiry"
										class="form-control input-sm" placeholder="Shelf Life" form="asyncForm"/>
								</td>
								<!-- Storage state drop down -->
								<td>
									<select name="storage_state" class= "form-control" ng-model="ctrl.item.storageState" form="asyncForm">
										<option value="" disabled selected>Select storage option</option>
				    					<option value="Pantry">Pantry</option>
    									<option value="Refrigerator">Refrigerator</option>
    									<option value="Freezer" selected>Freezer</option>
    								</select>
								</td>
								<!-- Purchase date field -->
								<td>
									<input type="text" ng-model="ctrl.item.purchaseDate" id="purchase_date"
										class="form-control input-sm" placeholder="Purchase date" form="asyncForm"/>
								</td>
								<!-- Expiry Date -->
								<td>									
									<input type="text" ng-model="ctrl.item.expiryDate" id="expiry_date"
										class="form-control input-sm" placeholder="Expiry date" form="asyncForm"/>
								</td>
								<!-- Add and Reset Buttons -->
								<td>
									<button type="submit" class="btn btn-success" data-toggle="tooltip" data-placement="right" title="Submit" form="asyncForm">
										<i class="fas fa-plus"></i>
									</button>
									<button type="button" ng-click="ctrl.reset()" class="btn btn-primary" form="asyncForm"
										 ng-disabled="asyncForm.$pristine" data-toggle="tooltip" data-placement="right" title="Clear">
										<i class="fas fa-backspace"></i>
									</button>
								</td>
							</tr>
							<tr ng-repeat="i in ctrl.items">
								<td ng-bind="i.name"></td>
								<td ng-bind="i.shelfLife"></td>
								<td ng-bind="i.storageState"></td>
								<td ng-bind="i.purchaseDate|date:'MM-dd-yyyy'"></td>
								<td ng-bind="i.expiryDate|date:'MM-dd-yyyy'"></td>
								<td>
									<button type="button" ng-click="ctrl.edit(i)" class="btn btn-warning" >
										<i class="fas fa-edit"></i>
									</button>
									<button type="button" ng-click="ctrl.remove(i.id)" class="btn btn-danger" >
										<i class="fas fa-trash"></i>
									</button>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
				<!-- Table container div -->

				<!-- List view of Items -->
			</div>

			<!-- Map page to controller -->
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div id="center_button">
			<button id="backFromAsync" onclick="window.location.href = 'welcome';">Back</button>
		</div>
	<!-- Center div -->
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"
		type="text/javascript"></script>
	<script src="<c:url value='/static/js/app.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/static/js/service/item_service.js' />"
		type="text/javascript"></script>
	<script
		src="<c:url value='/static/js/controller/item_controller.js' />"
		type="text/javascript"></script>

</body>
</html>