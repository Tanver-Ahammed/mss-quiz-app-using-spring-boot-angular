import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import Swal from 'sweetalert2';
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService,
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
  }

  formSubmit() {
    if (this.user.username == '' || this.user.username == null) {
      //alert("username is required!!");
      this.snack.open("Username is required!!", 'ok', {
        duration: 1000,
        verticalPosition: 'top'
      });
      console.log(this.user);
      return;
    }

    if (this.user.email == '' || this.user.email == null || !this.user.email.endsWith('@mbstu.ac.bd')) {
      this.snack.open("email is required!!", 'ok', {
        duration: 1000,
        verticalPosition: 'top'
      });
      return;
    }

    // student id
    let id_batch = this.user.email.substring(2, 7);
    // @ts-ignore
    if (isNaN(id_batch)) {
      // for teacher
      this.user.studentId = 'Teacher';
      this.user.batch = 'Teacher';
    } else {
      // for student
      this.user.studentId = 'IT'.concat(id_batch);
      this.user.batch = (parseInt(id_batch.substring(0, 2)) - 3).toString();
    }
    console.log(this.user)


    // call addUser
    this.userService.addUser(this.user).subscribe(
      (data: any) => {
        // success
        console.log(data);
        // alert("Registration Success...");
        Swal.fire('Success Done!!', 'User ID is: ' + data.id, 'success');
        this.router.navigateByUrl('/login');
      }, (error) => {
        console.log(error);
        // alert("Registration Failed...");
        this.snack.open('User with this username or email exist here!!', 'ok', {
          duration: 3000
        })
      }
    );

  }

}
