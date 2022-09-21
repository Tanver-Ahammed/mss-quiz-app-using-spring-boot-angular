import {Component, OnInit} from '@angular/core';
import {QuizService} from "../../../../services/quiz.service";
import Swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes = [
    {
      id: 0,
      title: '',
      description: '',
      maxMarks: 0,
      numberOfQuestions: 0,
      active: true,
      categoryDTO: {
        id: 0,
        title: ''
      },
      userDTO: {
        id: 0,
        username: ''
      }
    }
  ]

  constructor(private quizService: QuizService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.quizService.fetchQuizzes().subscribe(
      (data: any) => {
        this.quizzes = data;
        console.log(data)
      }, (error) => {
        Swal.fire("Error!!", "Error in fetching data!!", 'error');
      }
    );
  }

  // delete quiz
  deleteQuiz(quizId: number) {
    Swal.fire({
        icon: 'info',
        title: 'Are You Sure Wand to Delete?',
        confirmButtonText: 'Delete',
        showCancelButton: true
      }
    ).then((result) => {
      if (result.isConfirmed) {
        // delete method call
        this.quizService.deleteQuiz(quizId).subscribe(
          (data: any) => {
            this.quizzes = this.quizzes.filter(quiz => quiz.id != quizId);
            Swal.fire("Success!!", 'Quiz deleted successfully...', 'success');
          },
          (error) => Swal.fire("Error!!", 'Error in deleted Quiz!!!', 'error')
        );

      }
    });
  }

}
