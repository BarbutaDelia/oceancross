import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CruisePortCardComponent } from './cruise-port-card.component';

describe('CruisePortCardComponent', () => {
  let component: CruisePortCardComponent;
  let fixture: ComponentFixture<CruisePortCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CruisePortCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CruisePortCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
