import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../../../services/login.service";

@Component({
  selector: 'app-admin-sidebar',
  templateUrl: './admin-sidebar.component.html',
  styleUrls: ['./admin-sidebar.component.css']
})
export class AdminSideBarComponent implements OnInit {

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
  }

  // logout method
  logout() {
    this.loginService.logout();
    window.location.href = '/';
  }

}
