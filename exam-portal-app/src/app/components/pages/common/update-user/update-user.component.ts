import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../../../services/login.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {UserService} from "../../../../services/user.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  constructor(private loginService: LoginService,
              private userService: UserService,
              private snack: MatSnackBar,
              private router: Router) {
  }

  public user = {
    id: '',
    studentId: '',
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    batch: '',
    phone: '',
    about: '',
    password: ''
  }

  ngOnInit(): void {
    this.user = this.loginService.getUserDetails();
  }

  // update user
  updateUserForm() {
    this.userService.updateUser(this.user).subscribe(
      (data: any) => {
        Swal.fire('Success!!', 'User is updated Successfully...', 'success')
          .then(e => this.router.navigateByUrl('/'));
      },
      (error: any) => {
        Swal.fire('Error!!', 'Server Error!!! Try Again...', 'error');
      }
    );
  }

}
