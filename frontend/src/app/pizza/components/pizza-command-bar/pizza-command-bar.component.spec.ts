import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaCommandBarComponent } from './pizza-command-bar.component';

describe('PizzaCommandBarComponent', () => {
  let component: PizzaCommandBarComponent;
  let fixture: ComponentFixture<PizzaCommandBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PizzaCommandBarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PizzaCommandBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
