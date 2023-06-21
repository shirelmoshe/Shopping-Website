import { Component, OnInit } from '@angular/core';
import { SalesPerson } from './sales-person/sales-person.component';

@Component({
  selector: 'sales-person-list',
  templateUrl: './sales-person-list.component.html',
  styleUrls: ['./sales-person-list.component.css']
})
export class SalesPersonListComponent implements OnInit {
  salesPersonList: SalesPerson[] = [
    new SalesPerson("Anup", "Kumar", "anup.kumar", 5000),
    new SalesPerson("Anup", "Kumar", "anup.kumar", 5500),
    new SalesPerson("Anup", "Kumar", "anup.kumar", 84522)
  ];

  constructor() { }

  ngOnInit() {
    // Initialization logic
  }
}
