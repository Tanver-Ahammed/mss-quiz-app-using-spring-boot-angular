import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../../services/login.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn: boolean = false;
  user: any = null;
  userRole: string = '';

  constructor(public loginService: LoginService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.loginService.isLoggedIn();
    this.user = this.loginService.getUserDetails();
    this.userRole = this.loginService.getUserRole();
    this.loginService.loginStatusSubject.subscribe(data => {
      this.isLoggedIn = this.loginService.isLoggedIn();
      this.user = this.loginService.getUserDetails();
    });
  }

  public logout() {
    this.loginService.logout();
    window.location.reload();
    // this.loginService.loginStatusSubject.next(false);
  }
}
