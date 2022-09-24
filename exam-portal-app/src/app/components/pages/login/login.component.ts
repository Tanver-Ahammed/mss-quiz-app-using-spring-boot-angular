import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginService} from "../../../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
    username: '',
    password: ''
  }

  constructor(private loginService: LoginService,
              private snack: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  loginFormSubmit() {
    console.log("login button click");
    if (this.loginData.username.trim() == '' || this.loginData.username == null) {
      this.snack.open('user name is required!!', 'ok', {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      return;
    }
    if (this.loginData.password.trim() == '' || this.loginData.password == null) {
      this.snack.open('password is required!!', 'ok', {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      return;
    }

    // request server to generate token
    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log('login success');
        console.log(data);

        // login...
        this.loginService.loginUserForSetToken(data.token);
        this.loginService.getCurrentUser().subscribe(
          (user: any) => {
            console.log(user);
            console.log('login success...');
            this.loginService.setUserDetails(user);

            // redirect ADMIN: admin dashboard
            if (this.loginService.getUserRole() === "ADMIN") {
              window.location.href = '/admin';
              // this.router.navigateByUrl('/admin');
              this.loginService.loginStatusSubject.next(true);
            }
            // redirect NORMAL: normal dashboard
            else if (this.loginService.getUserRole() === "NORMAL") {
              window.location.href = '/user';
              // this.router.navigateByUrl('/user');
              this.loginService.loginStatusSubject.next(true);
            } else {
              this.loginService.logout();
              location.reload();
              this.router.navigateByUrl('/login');
            }


          }
        );

      }, (error) => {
        console.log('login failed!!');
        console.log(error);
        this.snack.open("Invalid Details !! Try Again...", 'OK', {
          duration: 3000
        });
      }
    )

  }
}
