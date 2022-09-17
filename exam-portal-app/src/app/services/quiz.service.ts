import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http:HttpClient) { }

  // fetch all quizzes
  public fetchQuizzes() {
    return this.http.get(`${baseUrl}/quiz/`);
  }

}
