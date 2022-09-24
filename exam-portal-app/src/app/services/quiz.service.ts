import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http: HttpClient) {
  }

  // add quiz
  public addQuiz(quiz: any) {
    return this.http.post(`${baseUrl}/quiz/`, quiz);
  }

  // fetch all quizzes
  public fetchQuizzes() {
    return this.http.get(`${baseUrl}/quiz/`);
  }

  // fetch all quizzes
  public fetchQuizzesByCategory(categoryId: number) {
    return this.http.get(`${baseUrl}/quiz/category/${categoryId}`);
  }

  // fetch all active quizzes for user
  public fetchAllActiveQuizzes() {
    return this.http.get(`${baseUrl}/quiz/active/`);
  }

  // fetch all active quizzes by category for user
  public fetchAllActiveQuizzesByCategory(categoryId: number) {
    return this.http.get(`${baseUrl}/quiz/active/category/${categoryId}`);
  }

  // delete quiz
  public deleteQuiz(quizId: number) {
    return this.http.delete(`${baseUrl}/quiz/${quizId}`);
  }

  // fetch single quiz
  public fetchSingleQuiz(quizId: number) {
    return this.http.get(`${baseUrl}/quiz/${quizId}`);
  }

  // update quiz
  public updateQuiz(quiz: any) {
    return this.http.put(`${baseUrl}/quiz/`, quiz);
  }

}
