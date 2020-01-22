(function() {
	'use strict';

	angular.module('itemTracker').controller('ItemController', ItemController);

	ItemController.inject = [ 'ItemService', 'RepoItemService', '$log' ];

	function ItemController(ItemService, RepoItemService, $log) {
		var self = this;
		self.item = {
			id : null,
			name : '',
			shelfLife : '',
			purchaseDate : null,
			expiryDate : null,
			storageState : '',
			refrigerateDateString : null,
			pantyrDateString : null,
			freezerDateString : null
		};
		self.items = [];
		self.repoItems = [];
		self.dItem = null;

		self.submit = submit;
		self.edit = edit;
		self.remove = remove;
		self.reset = reset;
		self.autofill = autofill;

		fetchAllItems();
		fetchAllRepoItems();

		function fetchAllRepoItems() {
			RepoItemService.fetchAllRepoItems().then(
					function(response) {
						self.repoItems = response;
						$log.debug("item_controller: Initializing repo with "
								+ self.repoItems.length + " items");
					}, function(errResponse) {
						$log.error('item_controller: Error initializing repo');
					})
		}

		function fetchAllItems() {
			ItemService.fetchAllItems().then(function(response) {
				self.items = response;
				$log.debug("Fetching items " + self.items);
			}, function(errResponse) {
				$log.error('Error while fetching Items');
			});
		}

		function createItem(item) {
			ItemService.createItem(item).then(function() {
				fetchAllItems();
				reset();
			}, function(errResponse) {
				$log.error('Error while creating Item');
			});
		}

		function updateItem(item, id) {
			ItemService.updateItem(item, id).then(function() {
				fetchAllItems();
				reset();
			}, function(errResponse) {
				$log.error('Error while updating Item');
			});
		}

		function deleteItem(id) {
			ItemService.deleteItem(id).then(fetchAllItems,
					function(errResponse) {
						$log.error('Error while deleting Item');
					});
		}

		function submit() {
			if (self.item.id === null) {
				$log.debug('Saving New Item', self.item);
				createItem(self.item);
			} else {
				updateItem(self.item, self.item.id);
				$log.debug('Item updated with id ', self.item.id);
			}
			// reset();
		}

		function edit(item) {
			$log.debug('item to be edited', item);
			self.item = angular.copy(item);
		}

		function remove(id) {
			$log.debug('id to be deleted', id);
			if (self.item.id === id) {// clean form if the item to be deleted
				// is shown there.
				reset();
			}
			deleteItem(id);
		}

		function reset() {
			self.item = {
				id : null,
				name : '',
				shelfLife : '',
				purchaseDate : null,
				expiryDate : null,
				storageState : '',
				refrigerateDateString : null,
				pantyrDateString : null,
				freezerDateString : null
			};
		}
		function autofill($item, $model, $label, $event) {
			$log.log("item=", $item, "model=", $model, "label=", $label,
					"event=", $event);
			$model.name = $item.rName;
			$model.shelfLife = $item.rFridgeDate;

		}

	}
})();
