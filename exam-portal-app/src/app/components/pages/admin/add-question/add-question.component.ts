import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {QuestionService} from "../../../../services/question.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import Swal from "sweetalert2";
// @ts-ignore
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';


@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

  public Editor = ClassicEditor;

  quizId: number = 0;
  quizTitle: string = '';
  question: any = {
    id: '',
    content: '',
    image: '',
    answer: '',
    option1: '',
    option2: '',
    option3: '',
    option4: '',
    quizDTO: {
      id: '',
      title: ''
    }
  }

  constructor(private activatedRoute: ActivatedRoute,
              private questionService: QuestionService,
              private snack: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
    this.quizId = this.activatedRoute.snapshot.params['quizId'];
    this.quizTitle = this.activatedRoute.snapshot.params['quizTitle'];
    this.question.quizDTO.id = this.quizId;
  }

  questionFromSubmit() {
    if (this.question.content.trim() == '' || this.question.content == null) {
      this.snack.open('Question Content Required!!', 'OK', {
        duration: 2000
      })
      return;
    }
    if (this.question.answer.trim() == '' || this.question.answer == null) {
      this.snack.open('Question Answer Required!!', 'OK', {
        duration: 2000
      })
      return;
    }

    this.questionService.addQuestion(this.question).subscribe(
      data => {
        Swal.fire('Success!!', 'Question Submitted Successfully...', 'success');
        this.router.navigateByUrl(`/admin/view-questions/${this.quizId}/${this.quizTitle}`);
      }, error => {
        Swal.fire('Error!!', 'Error Adding Question...', 'error');
      }
    );

  }
}
