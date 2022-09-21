import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {QuestionService} from "../../../../services/question.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-update-quiz-question',
  templateUrl: './update-quiz-question.component.html',
  styleUrls: ['./update-quiz-question.component.css']
})
export class UpdateQuizQuestionComponent implements OnInit {

  questionId: number = 0;
  question: any = null;

  constructor(private activatedRoute: ActivatedRoute,
              private questionService: QuestionService,
              private snack: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
    this.questionId = this.activatedRoute.snapshot.params['questionId'];
    this.questionService.fetchSingleQuestionById(this.questionId).subscribe(
      data => this.question = data,
      (error) => Swal.fire('Error!!', 'error is loading data from server!!', 'error')
    );
  }

  // update quiz data
  updateQuestionForm() {
    if (this.question.content.trim() == '' || this.question.content == null) {
      this.snack.open('Title Required!!', 'OK', {
        duration: 2000,
      });
      return;
    }
    // other validation...


    // update function call
    this.questionService.updateQuestion(this.question).subscribe(
      (data: any) => {
        Swal.fire('Success!!', 'Question is updated Successfully...', 'success')
          .then(e => this.router.navigateByUrl(`/admin/view-questions/${this.question.quizDTO.id}/${this.question.quizDTO.title}`));
      },
      (error: any) => {
        Swal.fire('Error!!', 'Server Error!!! Try Again...', 'error');
      }
    );
  }

}
