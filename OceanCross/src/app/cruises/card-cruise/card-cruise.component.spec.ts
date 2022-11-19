import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardCruiseComponent } from './card-cruise.component';

describe('CardCruiseComponent', () => {
  let component: CardCruiseComponent;
  let fixture: ComponentFixture<CardCruiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardCruiseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardCruiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
