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
    firstName: '',
    lastName: '',
    username: '',
    email: '',
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
