import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NavbarComponent} from './components/component/navbar/navbar.component';
import {FooterComponent} from './components/component/footer/footer.component';
import {SignupComponent} from './components/pages/signup/signup.component';
import {LoginComponent} from './components/pages/login/login.component';
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {HomeComponent} from './components/pages/home/home.component';
import {MatCardModule} from "@angular/material/card";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {authInterceptorProviders} from './services/auth.interceptor';
import {UserDashboardComponent} from './components/pages/user/user-dashboard/user-dashboard.component';
import {AdminDashboardComponent} from './components/pages/admin/admin-dashboard/admin-dashboard.component';
import {ProfileComponent} from './components/pages/profile/profile.component';
import {MatListModule} from "@angular/material/list";
import {AdminSideBarComponent} from './components/pages/admin/admin-sidebar/admin-sidebar.component';
import {ViewCategoriesComponent} from './components/pages/common/view-categories/view-categories.component';
import {AddCategoryComponent} from './components/pages/admin/add-category/add-category.component';
import {ViewQuizzesComponent} from './components/pages/common/view-quizzes/view-quizzes.component';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatSelectModule} from "@angular/material/select";
import {MatOptionModule} from "@angular/material/core";
import {AddQuizComponent} from './components/pages/admin/add-quiz/add-quiz.component';
import {UpdateQuizComponent} from './components/pages/admin/update-quiz/update-quiz.component';
import {ViewQuizQuestionsComponent} from './components/pages/common/view-quiz-questions/view-quiz-questions.component';
import {AddQuestionComponent} from './components/pages/admin/add-question/add-question.component';
import {
  UpdateQuizQuestionComponent
} from './components/pages/admin/update-quiz-question/update-quiz-question.component';
import {CKEditorModule} from "@ckeditor/ckeditor5-angular";
import {UserSidebarComponent} from './components/pages/user/user-sidebar/user-sidebar.component';
import {UserWelcomeComponent} from './components/pages/user/user-welcome/user-welcome.component';
import {LoadQuizComponent} from './components/pages/user/load-quiz/load-quiz.component';
import {InstructionsComponent} from './components/pages/user/instructions/instructions.component';
import {StartQuizComponent} from './components/pages/user/start-quiz/start-quiz.component';
import {MatRadioModule} from "@angular/material/radio";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {NgxUiLoaderHttpModule, NgxUiLoaderModule} from "ngx-ui-loader";
import {
  UserSubmitQuizResultComponent
} from './components/pages/common/user-submit-quiz-result/user-submit-quiz-result.component';
import {QuizResultComponent} from './components/pages/admin/quiz-result/quiz-result.component';
import {MatTableModule} from "@angular/material/table";
import {AdminWelcomeComponent} from './components/pages/admin/admin-welcome/admin-welcome.component';
import {WelcomeComponent} from './components/pages/super-admin/welcome/welcome.component';
import {DashboardComponent} from './components/pages/super-admin/dashboard/dashboard.component';
import { SidebarComponent } from './components/pages/super-admin/sidebar/sidebar.component';
import { ViewAllUsersComponent } from './components/pages/common/view-all-users/view-all-users.component';
import { UpdateRoleComponent } from './components/pages/super-admin/update-role/update-role.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    UserDashboardComponent,
    AdminDashboardComponent,
    ProfileComponent,
    AdminSideBarComponent,
    AdminWelcomeComponent,
    ViewCategoriesComponent,
    AddCategoryComponent,
    ViewQuizzesComponent,
    AddQuizComponent,
    UpdateQuizComponent,
    ViewQuizQuestionsComponent,
    AddQuestionComponent,
    UpdateQuizQuestionComponent,
    UserSidebarComponent,
    UserWelcomeComponent,
    LoadQuizComponent,
    InstructionsComponent,
    StartQuizComponent,
    UserSubmitQuizResultComponent,
    QuizResultComponent,
    WelcomeComponent,
    DashboardComponent,
    SidebarComponent,
    ViewAllUsersComponent,
    UpdateRoleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    NgbModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatOptionModule,
    ReactiveFormsModule,
    CKEditorModule,
    MatRadioModule,
    MatGridListModule,
    MatProgressSpinnerModule,
    NgxUiLoaderModule,
    NgxUiLoaderHttpModule.forRoot({
      showForeground: true
    }),
    MatTableModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
