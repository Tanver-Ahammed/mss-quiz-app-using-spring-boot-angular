import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class UserSubmitQuizService {

  constructor(private http: HttpClient) {
  }

  // user submit user
  public userSubmitQuiz(quiz: any) {
    return this.http.post(`${baseUrl}/user/submit/quiz/`, quiz);
  }

  // get user submit quiz by id
  public fetchSubmitQuizById(usqrId: number) {
    return this.http.get(`${baseUrl}/user/submit/quiz/${usqrId}`);
  }

}
