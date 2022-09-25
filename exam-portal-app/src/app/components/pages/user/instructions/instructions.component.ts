import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {QuizService} from "../../../../services/quiz.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {

  quizId: number = 0;
  quiz: any = {
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

  constructor(private activatedRoute: ActivatedRoute,
              private quizService: QuizService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.quizId = this.activatedRoute.snapshot.params['quizId'];

    this.quizService.fetchSingleQuiz(this.quizId).subscribe(
      (data) => this.quiz = data,
      (error) => Swal.fire('Error!!', 'Server Error loading quiz', 'error')
    );
  }

  startQuiz() {
    Swal.fire({
      title: 'Do you want to start the quiz?',
      showCancelButton: true,
      confirmButtonText: 'Start',
      denyButtonText: `Don't Start`,
      icon: 'info'
    }).then((result) => {
      if (result.isConfirmed) {
        this.router.navigateByUrl('/start-quiz/' + this.quizId + '/' + this.quiz.title);
      } else {
        Swal.fire('You are not interested Quiz!!!', 'Go Back', 'error')
      }
    });
  }

}
