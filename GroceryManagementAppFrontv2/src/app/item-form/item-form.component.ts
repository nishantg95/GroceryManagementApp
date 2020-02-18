import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';


@Component({
  // tslint:disable-next-line: component-selector
  selector: 'thead[app-item-form]',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.css']
})
export class ItemFormComponent implements OnInit {

  itemForm = new FormGroup({
    name: new FormControl(''),
    longevity: new FormControl(''),
    storageState: new FormControl(''),
    purchaseDate: new FormControl(''),
    expiryDate: new FormControl(''),
  });
  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    alert('done!');
  }

}
