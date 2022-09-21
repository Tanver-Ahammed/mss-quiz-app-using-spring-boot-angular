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
      }
    ]

  },
  {
    path: 'user',
    component: UserDashboardComponent,
    pathMatch: 'full',
    canActivate: [NormalGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
