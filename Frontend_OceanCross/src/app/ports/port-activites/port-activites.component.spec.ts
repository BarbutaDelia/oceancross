import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortActivitesComponent } from './port-activites.component';

describe('PortActivitesComponent', () => {
  let component: PortActivitesComponent;
  let fixture: ComponentFixture<PortActivitesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortActivitesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PortActivitesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
