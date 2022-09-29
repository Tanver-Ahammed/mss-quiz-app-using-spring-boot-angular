import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../../../services/login.service";

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  // logout method
  logout() {
    this.loginService.logout();
    window.location.href = '/';
  }

}
