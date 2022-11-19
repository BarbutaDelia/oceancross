import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAuthMenuComponent } from './user-auth-menu.component';

describe('UserAuthMenuComponent', () => {
  let component: UserAuthMenuComponent;
  let fixture: ComponentFixture<UserAuthMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAuthMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserAuthMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
