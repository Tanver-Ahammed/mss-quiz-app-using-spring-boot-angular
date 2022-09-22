import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Route, Router} from "@angular/router";
import {QuizService} from "../../../../services/quiz.service";
import Swal from "sweetalert2";
import {CategoryService} from "../../../../services/category.service";
import {MatSnackBar} from "@angular/material/snack-bar";
// @ts-ignore
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {

  public Editor = ClassicEditor;

  quizId: number = 0;
  quiz: any = null;
  categories: any = [];

  constructor(private activatedRoute: ActivatedRoute,
              private quizService: QuizService,
              private categoryService: CategoryService,
              private snack: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
    this.quizId = this.activatedRoute.snapshot.params['quizId'];
    this.quizService.fetchSingleQuiz(this.quizId).subscribe(
      data => this.quiz = data,
      error => {
        console.log(error);
        alert("Error!!")
      }
    );
    this.categoryService.fetchAllCategories().subscribe(
      (data) => (this.categories = data),
      (error) => Swal.fire('Error!!', 'error is loading data from server!!', 'error')
    );
  }

  // update quiz data
  updateQuizForm() {
    if (this.quiz.title.trim() == '' || this.quiz.title == null) {
      this.snack.open('Title Required!!', 'OK', {
        duration: 2000,
      });
      return;
    }
    // other validation...


    // update function call
    this.quizService.updateQuiz(this.quiz).subscribe(
      (data: any) => {
        Swal.fire('Success!!', 'Quiz is updated Successfully...', 'success')
          .then(e => this.router.navigateByUrl('/admin/quizzes'));
      },
      (error: any) => {
        Swal.fire('Error!!', 'Server Error!!! Try Again...', 'error');
      }
    );
    console.log(this.quiz)
  }

}
