import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSubmitQuizResultComponent } from './user-submit-quiz-result.component';

describe('UserSubmitQuizResultComponent', () => {
  let component: UserSubmitQuizResultComponent;
  let fixture: ComponentFixture<UserSubmitQuizResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserSubmitQuizResultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserSubmitQuizResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
