import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignupComponent} from "./components/pages/signup/signup.component";
import {LoginComponent} from "./components/pages/login/login.component";
import {HomeComponent} from "./components/pages/home/home.component";
import {AdminDashboardComponent} from "./components/pages/admin/admin-dashboard/admin-dashboard.component";
import {UserDashboardComponent} from "./components/pages/user/user-dashboard/user-dashboard.component";
import {AdminGuard} from "./services/admin.guard";
import {NormalGuard} from "./services/normal.guard";
import {ProfileComponent} from "./components/pages/profile/profile.component";
import {WelcomeComponent} from "./components/pages/admin/welcome/welcome.component";
import {ViewCategoriesComponent} from "./components/pages/common/view-categories/view-categories.component";
import {AddCategoryComponent} from "./components/pages/admin/add-category/add-category.component";
import {ViewQuizzesComponent} from "./components/pages/common/view-quizzes/view-quizzes.component";
import {AddQuizComponent} from "./components/pages/admin/add-quiz/add-quiz.component";
import {UpdateQuizComponent} from "./components/pages/admin/update-quiz/update-quiz.component";
import {ViewQuizQuestionsComponent} from "./components/pages/common/view-quiz-questions/view-quiz-questions.component";
import {AddQuestionComponent} from "./components/pages/admin/add-question/add-question.component";
import {
  UpdateQuizQuestionComponent
} from "./components/pages/admin/update-quiz-question/update-quiz-question.component";
import {UserWelcomeComponent} from "./components/pages/user/user-welcome/user-welcome.component";
import {LoadQuizComponent} from "./components/pages/user/load-quiz/load-quiz.component";
import {InstructionsComponent} from "./components/pages/user/instructions/instructions.component";
import {StartQuizComponent} from "./components/pages/user/start-quiz/start-quiz.component";
import {
  UserSubmitQuizResultComponent
} from "./components/pages/common/user-submit-quiz-result/user-submit-quiz-result.component";
import {QuizResultComponent} from "./components/pages/admin/quiz-result/quiz-result.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'signup',
    component: SignupComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'admin',
    component: AdminDashboardComponent,
    canActivate: [AdminGuard],
    children: [
      {
        path: '',
        component: WelcomeComponent
      },
      {
        path: 'profile',
        component: ProfileComponent,
      },
      {
        path: 'categories',
        component: ViewCategoriesComponent,
      },
      {
        path: 'add-category',
        component: AddCategoryComponent,
      },

      {
        path: 'quizzes',
        component: ViewQuizzesComponent,
      }, {
        path: 'add-quiz',
        component: AddQuizComponent
      }, {
        path: 'quiz/update/:quizId',
        component: UpdateQuizComponent
      }, {
        path: 'view-questions/:quizId/:quizTitle',
        component: ViewQuizQuestionsComponent
      }, {
        path: 'add-question/:quizId/:quizTitle',
        component: AddQuestionComponent
      }, {
        path: 'question/update/:questionId',
        component: UpdateQuizQuestionComponent
      }, {
        path: 'quiz-results/:quizId/:quizTitle',
        component: QuizResultComponent
      },
    ]
  },
  {
    path: 'submit/quiz/result/:usqrId',
    component: UserSubmitQuizResultComponent
  },
  {
    path: 'user',
    component: UserDashboardComponent,
    canActivate: [NormalGuard],
    children: [
      {
        path: '',
        component: UserWelcomeComponent
      },
      {
        path: 'profile',
        component: ProfileComponent
      },
      {
        path: 'quizzes/:categoryId',
        component: LoadQuizComponent
      },
      {
        path: 'instructions/:quizId',
        component: InstructionsComponent
      }
    ]
  }, {
    path: 'start-quiz/:quizId/:quizTitle',
    component: StartQuizComponent,
    canActivate: [NormalGuard]
  }, {
    path: 'submit/quiz/result/:usqrId',
    component: UserSubmitQuizResultComponent,
    canActivate: [NormalGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
