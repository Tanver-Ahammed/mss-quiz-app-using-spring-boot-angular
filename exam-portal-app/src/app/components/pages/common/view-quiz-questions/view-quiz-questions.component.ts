import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../../../../services/question.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent implements OnInit {
  quizId: number = 0;
  quizTitle: string = '';
  questions: any = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private questionService: QuestionService) {
  }

  ngOnInit(): void {
    this.quizId = this.activatedRoute.snapshot.params['quizId'];
    this.quizTitle = this.activatedRoute.snapshot.params['quizTitle'];
    this.questionService.fetchAllQuestionByQuizId(this.quizId).subscribe(
      data => this.questions = data,
      error => {
        console.log(error);
        alert("Error!!")
      }
    );
  }

  // deleteQuestion(questionId: number) {
  //   Swal.fire({
  //     icon: 'info',
  //     title: 'Are You Sure Wand to Delete?',
  //     confirmButtonText: 'Delete',
  //     showCancelButton: true
  //   }).then((result) => {
  //     if (result.isConfirmed) {
  //       // delete method call
  //       this.questionService.deleteQuestion(questionId).subscribe(
  //         (data) => {
  //           this.questions = this.questions.filter((question1: { id: number; }) => question1.id != questionId);
  //           Swal.fire("Success!!", 'Question deleted successfully...', 'success');
  //         },
  //         (error) => Swal.fire("Error!!", 'Error in deleted Quiz!!!', 'error')
  //       );
  //     }
  //   });
  // }

  // delete quiz
  deleteQuestion(questionId: number) {
    Swal.fire({
        icon: 'info',
        title: 'Are You Sure Wand to Delete Question?',
        confirmButtonText: 'Delete',
        showCancelButton: true
      }
    ).then((result) => {
      if (result.isConfirmed) {
        // delete method call
        this.questionService.deleteQuestion(questionId).subscribe(
          (data: any) => {
            this.questions = this.questions.filter((question: { id: number; }) => question.id != questionId);
            Swal.fire("Success!!", 'Quiz deleted successfully...', 'success');
          },
          (error) => Swal.fire("Error!!", 'Error in deleted Quiz!!!', 'error')
        );

      }
    });
  }


}
