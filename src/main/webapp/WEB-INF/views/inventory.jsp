<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!--need this for c: tag  -->

<html>
<head>
<jsp:include page="head.jsp" />
<script src="https://kit.fontawesome.com/f449710536.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.css" />
</head>

<body data-ng-app="itemTracker">
	<div class="container-fluid"
		data-ng-controller="ItemController as ctrl">
		<div class="row justify-content-center p-5">
			<h3>Nishant's Inventory</h3>
		</div>
		<form autocomplete="off" data-ng-submit="ctrl.submit()" id="asyncForm"
			class="form-inline"></form>
		<div class="panel">
			<div class="tablecontainer">
				<table class="table table-light table-hover .w-auto" id="items">
					<caption>
						<h3>Manage Items</h3>
					</caption>
					<thead>
						<tr>
							<th class="required">Name</th>
							<th class="required">Storage Location</th>
							<th>Longevity</th>
							<th class="required">Purchase Date</th>
							<th>Expiry Date</th>
							<th></th>
						</tr>
						<tr class="table-info">
							<th>
								<div class="name-typeahead">
									<input type="text" data-ng-model="ctrl.item.name" id="name"
										class="form-control input-sm" placeholder="Item name"
										form="asyncForm" data-ng-required="true"
										uib-typeahead="rItem as rItem.rName for rItem in ctrl.repoItems | filter:$viewValue | limitTo:10"
										typeahead-on-select="ctrl.test($item, ctrl.item, $label, $event)" />
								</div>
							</th>
							<th><select name="storage_state" class="form-control"
								data-ng-model="ctrl.item.storageState" data-ng-required="true"
								form="asyncForm">
									<option value="" disabled selected>Select storage
										option</option>
									<option value="Pantry">Pantry</option>
									<option value="Refrigerator">Refrigerator</option>
									<option value="Freezer" selected>Freezer</option>
							</select></th>
							<th><input type="text" data-ng-model="ctrl.item.shelfLife"
								id="shelf_life" class="form-control input-sm"
								placeholder="Shelf Life" form="asyncForm" /></th>
							<th><input type="text"
								data-ng-model="ctrl.item.purchaseDate" data-ng-required="true"
								id="purchase_date" class="form-control input-sm"
								placeholder="Purchase date" form="asyncForm" /></th>
							<th><input type="text" data-ng-model="ctrl.item.expiryDate"
								id="expiry_date" class="form-control input-sm"
								placeholder="Expiry date" form="asyncForm" />
							<th>
								<div class="text-nowrap">
									<button type="submit" class="btn btn-success"
										data-toggle="tooltip" data-placement="right" title="Submit"
										form="asyncForm" id="addChangeButton">
										<i class="fas fa-plus"></i>
									</button>
									<button type="button" data-ng-click="ctrl.reset()"
										class="btn btn-primary" form="asyncForm"
										data-ng-disabled="asyncForm.$pristine" data-toggle="tooltip"
										data-placement="right" title="Clear">
										<i class="fas fa-backspace"></i>
									</button>
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr class="animation" data-ng-repeat="i in ctrl.items">
							<td data-ng-bind="i.name"></td>
							<td data-ng-bind="i.storageState"></td>
							<td data-ng-bind="i.shelfLife"></td>
							<td data-ng-bind="i.purchaseDate|date:'MM-dd-yyyy'"></td>
							<td data-ng-bind="i.expiryDate|date:'MM-dd-yyyy'"></td>
							<td>
								<div class="text-nowrap">
									<button type="button" data-ng-click="ctrl.edit(i)"
										class="btn btn-warning">
										<i class="fas fa-edit"></i>
									</button>
									<button type="button" data-toggle="modal" data-ng-click="ctrl.dItem=i"
										data-target="#deleteConfirmation" class="btn btn-danger">
										<i class="fas fa-trash"></i>
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="row justify-content-center p-3">
					<button id="backFromAsync"
						onclick="window.location.href = 'welcome';">Back</button>
				</div>
			</div>
		</div>
			<!-- Modal alert for deletion -->
	<div class="modal fade" id="deleteConfirmation" tabindex="-1"
		role="dialog" aria-labelledby="deleteConfirmation" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteConfirmationTitle">Confirm
						Deletion</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Are you sure you want to delete the following item from your
						inventory?</p>
					<p><strong>{{ctrl.dItem.name}}</strong></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" data-ng-click="ctrl.remove(ctrl.dItem.id)">Yes</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.10/angular.min.js"
		type="text/javascript">
		
	</script>
	<script src="<c:url value='/static/js/app.js' />"
		type="text/javascript">
		
	</script>
	<script src="<c:url value='/static/js/service/item_service.js' />"
		type="text/javascript">
		
	</script>
	<script
		src="<c:url value='/static/js/controller/item_controller.js' />"
		type="text/javascript">
		
	</script>
	<script src="<c:url value='/static/js/ui-bootstrap.min.js' />"
		type="text/javascript">
		
	</script>
	<script src="<c:url value='/static/js/service/repo_item_service.js' />"
		type="text/javascript">
		
	</script>



</body>
</html>