import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuizService} from "../../../../services/quiz.service";

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {

  categoryId: number = 0;
  quizzes: any = [];

  constructor(private activatedRoute: ActivatedRoute,
              private quizService: QuizService) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.categoryId = params['categoryId'];

      if (this.categoryId == 0) {
        this.quizService.fetchAllActiveQuizzes().subscribe(
          (data: any) => {
            this.quizzes = data;
          },
          (error) => alert('Error loading data!!!')
        );
      } else {
        this.quizService.fetchAllActiveQuizzesByCategory(this.categoryId).subscribe(
          (data: any) => {
            this.quizzes = data;
          },
          (error) => {
            alert('Error loading data!!!')
          }
        );
      }
    });
  }


}
