import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortDetailsCardComponent } from './port-details-card.component';

describe('PortDetailsCardComponent', () => {
  let component: PortDetailsCardComponent;
  let fixture: ComponentFixture<PortDetailsCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortDetailsCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PortDetailsCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
