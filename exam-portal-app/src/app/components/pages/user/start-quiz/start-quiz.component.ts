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
  quiz: any = null;
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
    this.quizId = this.activatedRoute.snapshot.params['quizId'];
    this.quizTitle = this.activatedRoute.snapshot.params['quizTitle'];
    this.loadQuestions();
  }

  loadQuestions() {
    this.quizService.fetchSingleQuiz(this.quizId).subscribe(
      (data) => {
        this.quiz = data;
        this.timer = this.quiz.questionDTOS.length * 60;
        console.log(this.quiz)
        this.startTimer();
      },
      (error) => Swal.fire('Error!!', 'Server Error!!', 'error')
    )
  };

  userSubmitQuiz() {
    Swal.fire('Quiz Submit', 'Are want to submit Quiz?', 'info')
    this.userSubmitQuizService.userSubmitQuiz(this.quiz).subscribe(
      (data) => {
        this.userSubmitQuizResult = data;
        // this.router.navigateByUrl(`/user/quiz/result/`);
      }, (error) => {
        Swal.fire('Error!!', 'Quiz is not submitted!!!', 'error');
        this.router.navigateByUrl(`/user/instructions/${this.quizId}/`);
      }
    );
  }

  startTimer() {
    let time: any = window.setInterval(() => {
      if (this.timer <= 0) {
        this.userSubmitQuiz();
        clearInterval(time);
      } else {
        this.timer--;
      }
    }, 1000);
  }

  getFormatTimer() {
    let mm = Math.floor(this.timer / 60);
    let ss = Math.floor(this.timer - mm * 60);
    return `${mm} min : ${ss}`;
  }

}
