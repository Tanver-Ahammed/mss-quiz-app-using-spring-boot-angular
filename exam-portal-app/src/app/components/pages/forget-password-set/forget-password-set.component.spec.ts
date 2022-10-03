import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForgetPasswordSetComponent } from './forget-password-set.component';

describe('ForgetPasswordSetComponent', () => {
  let component: ForgetPasswordSetComponent;
  let fixture: ComponentFixture<ForgetPasswordSetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForgetPasswordSetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForgetPasswordSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
