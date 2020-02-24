import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ItemsService } from '../items.service';
import { Item } from '../item';


@Component({
  // tslint:disable-next-line: component-selector
  selector: 'thead[app-item-form]',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.css']
})
export class ItemFormComponent implements OnInit {

  itemForm = new FormGroup({
    id: new FormControl(''),
    name: new FormControl(''),
    longevity: new FormControl(''),
    storageState: new FormControl(''),
    purchaseDate: new FormControl(''),
    expiryDate: new FormControl(''),
    pantyrDateString: new FormControl(''),
    freezerDateString: new FormControl(''),
    refrigerateDateString: new FormControl('')
  });

  longevityOptions = ['Pantry', 'Refrigerator', 'Freezer'];

  item: Item;

  constructor(private itemsService: ItemsService) { }


  ngOnInit(): void {
    this.item = { id: null, name : 'kachow', longevity: null, purchaseDate: null, expiryDate: null,
      storageState: null, pantryDateString: '2000 days', freezerDateString: null, refrigerateDateString: null};
    }


  onSubmit(values: FormGroup) {
    // this.item.pantryDateString = '200';

    console.log('Values: ', values);
  }
  edit(item: Item) {
    console.log('The item is ',  item);
    this.itemForm.setValue(item);
    console.log(this.itemForm);

  }

}
