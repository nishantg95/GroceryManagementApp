import { Component, OnInit, Output, EventEmitter, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { ItemsService } from '../items.service';
import { Item } from '../item';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';




@Component({
  // tslint:disable-next-line: component-selector
  selector: 'thead[app-item-form]',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.css']
})
export class ItemFormComponent implements OnInit {

  longevityOptions = ['Pantry', 'Refrigerator', 'Freezer'];
  @Output() refreshList = new EventEmitter();
  item: Item;
  @ViewChild('itemForm') nameInput: ElementRef;
  model: any = {};


  constructor(private itemsService: ItemsService) { }

  ngOnInit(): void {
    this.resetItemForm();
  }

  resetItemForm(): void {
    this.item = this.itemsService.emptyItem();
  }

  onSubmit(): void {
    console.log(this.item);
    if (this.item.id == null) {
      this.itemsService.addItem(this.item).subscribe(() => { this.refreshList.emit(null); });
    } else {
      this.itemsService.updateItem(this.item).subscribe(() => { this.refreshList.emit(null); });
    }
    this.resetItemForm();


  }
  edit(item: Item): void {
    this.item = { ...item };
    console.log('child' , this.item);
  }

  // autofill = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term.length < 2 ? []: ))

  }
}
