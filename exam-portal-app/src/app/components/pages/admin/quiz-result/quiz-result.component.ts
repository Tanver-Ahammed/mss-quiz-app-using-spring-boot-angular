import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserSubmitQuizService} from "../../../../services/user-submit-quiz.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-quiz-result',
  templateUrl: './quiz-result.component.html',
  styleUrls: ['./quiz-result.component.css']
})
export class QuizResultComponent implements OnInit {

  quizId: number = 0;
  quizTitle: string = '';
  dataSource: any;
  userSubmitQuizResults: any = [
    {
      id: '',
      correctQuestions: '',
      quizDuration: '',
      userDTO: {
        id: '',
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        phone: '',
        about: ''
      },
      quizDTO: {
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
      },
    }
  ]

  constructor(private activatedRoute: ActivatedRoute,
              private userSubmitQuizService: UserSubmitQuizService) {
  }

  ngOnInit(): void {
    this.quizId = this.activatedRoute.snapshot.params['quizId'];
    this.quizTitle = this.activatedRoute.snapshot.params['quizTitle'];
    this.userSubmitQuizService.fetchSubmitQuizByQuizId(this.quizId).subscribe(
      (data: any) => {
        this.userSubmitQuizResults = data;
        this.dataSource = data;
        console.log(data)
      }, (error) => {
        Swal.fire("Error!!", "Server Error in fetching data!!", 'error');
      }
    );
  }


  /*****************************************/
  displayedColumns: string[] = ['user-id', 'user-name', 'correct-answer', 'success-percentage', 'quiz-result'];

  printPage(divName:any) {
    window.print();
  }
}
