import {Component, OnInit} from '@angular/core';
import {LocationStrategy} from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";
import Swal from "sweetalert2";
import {QuizService} from "../../../../services/quiz.service";
import {UserSubmitQuizService} from "../../../../services/user-submit-quiz.service";

@Component({
  selector: 'app-start-quiz',
  templateUrl: './start-quiz.component.html',
  styleUrls: ['./start-quiz.component.css']
})
export class StartQuizComponent implements OnInit {

  quizId: number = 0;
  quizTitle: any = null;
  isSubmittedQuiz: boolean = false;

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
    },
    questionDTOS: [
      {
        id: '',
        content: '',
        image: '',
        answer: '',
        option1: '',
        option2: '',
        option3: '',
        option4: '',
      }
    ]
  };
  userSubmitQuizResult: any = null;
  timer: any;

  constructor(private locationStrategy: LocationStrategy,
              private activatedRoute: ActivatedRoute,
              private quizService: QuizService,
              private userSubmitQuizService: UserSubmitQuizService,
              private router: Router) {
    // preventing back button in browser
    // @ts-ignore
    history.pushState(null, null, window.location.href);
    this.locationStrategy.onPopState(() => {
      // @ts-ignore
      return history.pushState(null, null, window.location.href);
    });
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.quizId = params['quizId'];
      this.quizTitle = params['quizTitle'];
      this.loadQuestions();
    })

  }

  loadQuestions() {
    this.quizService.fetchSingleQuiz(this.quizId).subscribe(
      (data) => {
        this.quiz = data;
        this.timer = this.quiz.questionDTOS.length * 60 / 5;
        console.log(this.quiz)
        this.startTimer();
      },
      (error) => Swal.fire('Error!!', 'Server Error!!', 'error')
    )
  };

  submitQuiz() {
    this.userSubmitQuizService.userSubmitQuiz(this.quiz).subscribe(
      (data) => {
        this.userSubmitQuizResult = data;
        this.router.navigateByUrl(`/submit/quiz/result/${this.userSubmitQuizResult.id}`);
      }, (error) => {
        Swal.fire('Error!!', 'Quiz is not submitted!!!', 'error');
        this.router.navigateByUrl(`/user/instructions/${this.quizId}/`);
      }
    );
  }

  userSubmitQuiz() {
    Swal.fire({
      title: 'Do you want to Submit the Quiz?',
      showCancelButton: true,
      confirmButtonText: 'Submit Quiz',
      icon: 'info'
    }).then((e) => {
      if (e.isConfirmed) {
        this.isSubmittedQuiz = true;
        this.submitQuiz();
      }
    });
  }

  evalQuizByTimer() {
    this.submitQuiz();
  }

  startTimer() {
    let time: any = window.setInterval(() => {
      if (this.timer <= 0) {
        this.evalQuizByTimer();
        clearInterval(time);
      } else if (this.isSubmittedQuiz)
        clearInterval(time);
      else
        this.timer--;
    }, 1000);
  }

  getFormatTimer() {
    let mm = Math.floor(this.timer / 60);
    let ss = Math.floor(this.timer - mm * 60);
    return `${mm} min : ${ss}`;
  }

}
