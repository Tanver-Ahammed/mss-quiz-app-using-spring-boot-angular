import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) {
  }

  // add question
  public addQuestion(question: any) {
    return this.http.post(`${baseUrl}/question/`, question);
  }

  // fetch all question by id
  public fetchAllQuestionByQuizId(quizId: number) {
    return this.http.get(`${baseUrl}/question/quiz/${quizId}`);
  }

  // fetch single question
  public fetchSingleQuestionById(questionId: number) {
    return this.http.get(`${baseUrl}/question/${questionId}`);
  }

  // update question
  public updateQuestion(question: any) {
    return this.http.put(`${baseUrl}/question/`, question);
  }

  // delete question
  public deleteQuestion(questionId: number) {
    return this.http.delete(`${baseUrl}/question/${questionId}`);
  }

}
