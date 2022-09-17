import {Component, OnInit} from '@angular/core';
import {QuizService} from "../../../../services/quiz.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes = [
    {
      id: 0,
      title: 'Java',
      description: 'Programming',
      maxMarks: 20,
      numberOfQuestions: 10,
      active: true,
      categoryDTO: {
        id: 0,
        title: 'Programming'
      }
    }
  ]

  constructor(private quizService: QuizService) {
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

}
