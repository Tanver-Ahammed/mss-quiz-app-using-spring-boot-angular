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
import {SideBarComponent} from './components/pages/admin/side-bar/side-bar.component';
import {WelcomeComponent} from './components/pages/admin/welcome/welcome.component';
import {ViewCategoriesComponent} from './components/pages/common/view-categories/view-categories.component';
import {AddCategoryComponent} from './components/pages/admin/add-category/add-category.component';
import {ViewQuizzesComponent} from './components/pages/common/view-quizzes/view-quizzes.component';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatSelectModule} from "@angular/material/select";
import {MatOptionModule} from "@angular/material/core";
import { AddQuizComponent } from './components/pages/admin/add-quiz/add-quiz.component';
import { UpdateQuizComponent } from './components/pages/admin/update-quiz/update-quiz.component';

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
    SideBarComponent,
    WelcomeComponent,
    ViewCategoriesComponent,
    AddCategoryComponent,
    ViewQuizzesComponent,
    AddQuizComponent,
    UpdateQuizComponent
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
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
