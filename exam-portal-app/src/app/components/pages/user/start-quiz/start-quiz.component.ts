import {Component, OnInit} from '@angular/core';
import {LocationStrategy} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import Swal from "sweetalert2";
import {QuizService} from "../../../../services/quiz.service";

@Component({
  selector: 'app-start-quiz',
  templateUrl: './start-quiz.component.html',
  styleUrls: ['./start-quiz.component.css']
})
export class StartQuizComponent implements OnInit {

  quizId: number = 0;
  quizTitle: any = null;
  quiz: any = null;

  constructor(private locationStrategy: LocationStrategy,
              private activatedRoute: ActivatedRoute,
              private quizService: QuizService) {
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
        console.log(this.quiz)
      },
      (error) => Swal.fire('Error!!', 'Server Error!!', 'error')
    )
  };

  submitQuiz() {
    console.log(this.quiz);
  }

}
