import { Component, OnInit, ViewChild } from '@angular/core';
import { ItemsService } from '../items.service';
import { Item } from '../item';
import { ItemFormComponent } from '../item-form/item-form.component';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {

  items: Item[];
  dItem = null;

  constructor(private itemsService: ItemsService) {
   }

  ngOnInit(): void {
    this.listAllItems();
  }

  listAllItems(): void {
    this.itemsService.listAllItems().subscribe(items => this.items = items);
  }

  deleteItem(item: Item) {
    this.itemsService.deleteItem(item).subscribe(() => {this.listAllItems(); });
  }

  edit(index: number) {
    console.log('I was remembered');
  }

}
