(function(){
	'use strict'
	
	angular
		.module('itemTracker')
		.factory('ItemService', ItemService);
	
	ItemService.inject = ['$http','$log'];
	
	function ItemService($http,$log){
		
		var REST_SERVICE_URI = "/GroceryManagementApp/data";
		var factory = {
				fetchAllItems: fetchAllItems,
				createItem: createItem,
				updateItem: updateItem,
				deleteItem: deleteItem
		};
		
		return factory;
		
		function fetchAllItems(){
			return $http.get(REST_SERVICE_URI+"/listAllItems").then(function(resp){
				$log.debug("Item_service:"+resp.data);
				return resp.data;
			}).catch(function(error){
				$log.error(error);
			});
			
		}
		
		function createItem(item){
			return $http.post(REST_SERVICE_URI+"/createItem", item).then(function(resp){
				$log.debug(resp.data);
			}).catch(function(error){
				$log.error(error);
			});
		}
		
		function updateItem(item, id){
			return $http.put(REST_SERVICE_URI+"/updateItem/"+id, item).then(function(resp){
				$log.debug(resp.data);
			}).catch(function(error){
				$log.error(error);
			});
		}
		
		function deleteItem(id) {
			return $http.delete(REST_SERVICE_URI+"/deleteItem/"+id).then(function(resp){
				$log.debug(resp.data);
			}).catch(function(error){
				$log.error(error);
			});
		}
		
	}
	
})();