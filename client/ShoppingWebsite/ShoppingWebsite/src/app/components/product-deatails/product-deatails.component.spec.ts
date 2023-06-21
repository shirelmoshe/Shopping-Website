import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductDeatailsComponent } from './product-deatails.component';

describe('ProductDeatailsComponent', () => {
  let component: ProductDeatailsComponent;
  let fixture: ComponentFixture<ProductDeatailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductDeatailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductDeatailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
