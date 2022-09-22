import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {CategoryService} from 'src/app/services/category.service';
import {LoginService} from 'src/app/services/login.service';
import {QuizService} from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';
// @ts-ignore
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css'],
})
export class AddQuizComponent implements OnInit {

  public Editor = ClassicEditor;

  categories: any = [];

  quizData = {
    id: '',
    title: '',
    description: '',
    maxMarks: '',
    numberOfQuestions: '',
    active: true,
    categoryDTO: {
      id: '',
      title: '',
    },
    userDTO: {
      id: '',
      name: '',
    },
  };

  constructor(
    private categoryService: CategoryService,
    private loginService: LoginService,
    private quizService: QuizService,
    private snack: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryService.fetchAllCategories().subscribe(
      (data) => (this.categories = data),
      (error) =>
        Swal.fire('Error!!', 'error is loading data from server!!', 'error')
    );
  }

  // add quiz data
  addQuizForm() {
    if (this.quizData.title.trim() == '' || this.quizData.title == null) {
      this.snack.open('Title Required!!', 'OK', {
        duration: 2000,
      });
      return;
    }

    // other validation
    this.quizData.userDTO.id = this.loginService.getUserDetails().id;

    // add function call
    this.quizService.addQuiz(this.quizData).subscribe(
      (data: any) => {
        Swal.fire('Success!!', 'Quiz is Successfully Added...', 'success');
        this.router.navigateByUrl('/admin/quizzes');
      },
      (error: any) => {
        Swal.fire('Error!!', 'Server Error!!! Try Again...', 'error');
      }
    );
  }
}
