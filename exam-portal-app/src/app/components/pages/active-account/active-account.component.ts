import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../services/user.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-active-account',
  templateUrl: './active-account.component.html',
  styleUrls: ['./active-account.component.css']
})
export class ActiveAccountComponent implements OnInit {

  public user = {
    id: '',
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    phone: '',
    about: '',
    verificationCode: '',
    password: ''
  }

  constructor(private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.user.username = this.activatedRoute.snapshot.params['username'];
    this.user.verificationCode = this.activatedRoute.snapshot.params['verificationCode'];
    this.activeAccount();
  }

  activeAccount() {
    this.userService.activeAccount(this.user).subscribe(
      (data: any) => {
        // success
        this.user = data;
        this.router.navigateByUrl(`/login`);
        Swal.fire('Password Set!!', 'Now, Login', 'success');
      }, (error) => {
        console.log(error);
        // alert("Registration Failed...");
        Swal.fire('Error!!', `your otp don't mach!! Try Again...`, 'error')
      }
    );
  }

}
