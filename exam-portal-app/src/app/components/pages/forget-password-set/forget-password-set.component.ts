import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import Swal from "sweetalert2";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-forget-password-set',
  templateUrl: './forget-password-set.component.html',
  styleUrls: ['./forget-password-set.component.css']
})
export class ForgetPasswordSetComponent implements OnInit {

  email: string = ''
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
    this.user.email = this.activatedRoute.snapshot.params['email'];
    alert(this.user.email)
  }

  forgetPasswordSet() {
    this.userService.forgetPasswordSet(this.user).subscribe(
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
