import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserSubmitQuizService} from "../../../../services/user-submit-quiz.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-user-submit-quiz-result',
  templateUrl: './user-submit-quiz-result.component.html',
  styleUrls: ['./user-submit-quiz-result.component.css']
})
export class UserSubmitQuizResultComponent implements OnInit {

  usqrId: any = 0;
  userSubmitQuizResult: any;
  qColor: string = '';

  quizResult: any = {
    id: 0,
    correctQuestions: '',
    quizDuration: '',
  };

  user: any = {
    id: '',
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    phone: '',
    about: '',
    password: ''
  }

  quiz: any = {
    id: '',
    title: '',
    description: '',
    maxMarks: '',
    numberOfQuestions: '',
    active: true,
    author: '',
    categoryDTO: {
      id: '',
      title: '',
    }
  }

  userQuestionAnswers: any = [
    {
      id: '',
      userAnswer: '',
      questionDTO: {
        id: '',
        content: '',
        image: '',
        answer: '',
        option1: '',
        option2: '',
        option3: '',
        option4: '',
      }
    }
  ]

  constructor(private activatedRoute: ActivatedRoute,
              private userSubmitQuizService: UserSubmitQuizService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.usqrId = this.activatedRoute.snapshot.params['usqrId'];
    this.userSubmitQuizResult = this.userSubmitQuizService.fetchSubmitQuizById(this.usqrId).subscribe(
      (data) => {
        this.userSubmitQuizResult = data;
        this.quizResult.id = this.userSubmitQuizResult.id;
        this.quizResult.correctQuestions = this.userSubmitQuizResult.correctQuestions;
        this.user = this.userSubmitQuizResult.userDTO;
        this.quiz = this.userSubmitQuizResult.quizDTO;
        this.userQuestionAnswers = this.userSubmitQuizResult.userQuestionAnswerStoreDTOS;
        this.userSubmitQuizResult = null;
      },
      error => {
        Swal.fire("Error!!", 'You have not credential!!', 'error');
        this.router.navigateByUrl('/user/profile');
      }
    )
  }

  questionColor(userAns: string, correctAns: string) {
    if (userAns == correctAns)
      this.qColor = 'green';
    else
      this.qColor = 'red';
  }

  printPage() {
    window.print();
  }
}
