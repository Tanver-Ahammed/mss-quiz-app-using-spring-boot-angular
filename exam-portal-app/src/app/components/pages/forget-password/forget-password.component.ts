import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {UserService} from "../../../services/user.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {

  public user = {
    id: '',
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    phone: '',
    about: '',
    password: ''
  }

  constructor(private userService: UserService,
              private snack: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  forgetEmailSubmit() {
    this.userService.forgetPassword(this.user).subscribe(
      (data: any) => {
        // success
        this.user = data;
        this.router.navigateByUrl(`/forget/password/set/${this.user.email}`);
        Swal.fire('Success Done!!', 'Check your email', 'success');
      }, (error) => {
        console.log(error);
        // alert("Registration Failed...");
        this.snack.open(`this user or email don't exist here!!`, 'ok', {
          duration: 3000
        })
      }
    );
  }
}
