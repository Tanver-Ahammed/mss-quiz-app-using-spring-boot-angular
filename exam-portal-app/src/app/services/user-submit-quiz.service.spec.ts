import { TestBed } from '@angular/core/testing';

import { UserSubmitQuizService } from './user-submit-quiz.service';

describe('UserSubmitQuizService', () => {
  let service: UserSubmitQuizService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserSubmitQuizService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
