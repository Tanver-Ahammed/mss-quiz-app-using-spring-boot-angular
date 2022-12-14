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
  categoryTitle: string = '';
  dataSource: any;
  userSubmitQuizResults: any = [
    {
      id: '',
      correctQuestions: '',
      quizDuration: '',
      userDTO: {
        id: '',
        studentId: '',
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        batch: '',
        phone: '',
        about: ''
      },
      quizDTO: {
        id: '',
        title: '',
        description: '',
        maxMarks: '',
        numberOfQuestions: '',
        pin: '',
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
        this.categoryTitle = this.userSubmitQuizResults[0].quizDTO.categoryDTO.title;
        // @ts-ignore
        this.dataSource.sort((a: any, b: any) => {
          if (a.userDTO.studentId < b.userDTO.studentId)
            return -1;
        });
        console.log(data)
      }, (error) => {
        Swal.fire("Error!!", "Server Error in fetching data!!", 'error');
      }
    );
  }


  /*****************************************/
  displayedColumns: string[] = ['student-id', 'user-name', 'correct-answer', 'success-percentage', 'quiz-result'];

  printPage(divName: any) {
    window.print();
  }

  batchNumber: any;
  tempUsersSubmitQuizResult: any = [];

  filterUsersByBatch() {
    for (let i = 0; i < this.userSubmitQuizResults.length; i++) {
      let batchNum = this.userSubmitQuizResults[i].userDTO.batch;
      if (batchNum.includes(this.batchNumber)) {
        this.tempUsersSubmitQuizResult = this.tempUsersSubmitQuizResult.concat(this.userSubmitQuizResults[i]);
      }
    }
    this.dataSource = this.tempUsersSubmitQuizResult;
    this.tempUsersSubmitQuizResult = [];
    return this.dataSource;
  }
}
