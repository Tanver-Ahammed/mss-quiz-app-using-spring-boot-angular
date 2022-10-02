import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../../services/user.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-view-all-users',
  templateUrl: './view-all-users.component.html',
  styleUrls: ['./view-all-users.component.css']
})
export class ViewAllUsersComponent implements OnInit {

  users: any = [
    {
      id: '',
      firstName: '',
      lastName: '',
      username: '',
      email: '',
      phone: '',
      about: '',
      password: '',
      roles: {
        id: '',
        roleName: ''
      }
    }
  ];

  constructor(private userService: UserService,
              private router: Router) {
  }

  displayedColumns: string[] = ['user-id', 'full-name', 'user-name', 'user-email', 'user-role', 'quiz-result'];

  ngOnInit(): void {
    this.userService.fetchAllUsers().subscribe(
      (data: any) => this.users = data,
      (error) => {
        Swal.fire("Error!!", "Error in fetching user!!", 'error');
      }
    )
  }

}
