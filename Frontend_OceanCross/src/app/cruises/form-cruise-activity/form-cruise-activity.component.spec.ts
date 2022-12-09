import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCruiseActivityComponent } from './form-cruise-activity.component';

describe('FormCruiseActivityComponent', () => {
  let component: FormCruiseActivityComponent;
  let fixture: ComponentFixture<FormCruiseActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormCruiseActivityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormCruiseActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
