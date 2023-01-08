import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortsMapComponent } from './ports-map.component';

describe('PortsMapComponent', () => {
  let component: PortsMapComponent;
  let fixture: ComponentFixture<PortsMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortsMapComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PortsMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
