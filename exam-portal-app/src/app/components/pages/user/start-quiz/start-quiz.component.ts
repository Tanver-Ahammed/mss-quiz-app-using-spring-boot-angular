import {Component, OnInit} from '@angular/core';
import {LocationStrategy} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../../../../services/question.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-start-quiz',
  templateUrl: './start-quiz.component.html',
  styleUrls: ['./start-quiz.component.css']
})
export class StartQuizComponent implements OnInit {

  quizId: number = 0;
  quizTitle: any = null;
  questions: any = [];

  constructor(private locationStrategy: LocationStrategy,
              private activatedRoute: ActivatedRoute,
              private questionService: QuestionService) {
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
    this.questionService.fetchAllQuestionByQuizId(this.quizId).subscribe(
      (data) => {
        this.questions = data;
        this.questions.forEach((q: any) => {
          q['givenAnswer'] = '';
        })
        console.log(this.questions)
      },
      (error) => Swal.fire('Error!!', 'Server Error!!', 'error')
    )
  };

}
