import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortsDropdownComponent } from './ports-dropdown.component';

describe('PortsDropdownComponent', () => {
  let component: PortsDropdownComponent;
  let fixture: ComponentFixture<PortsDropdownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortsDropdownComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PortsDropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
