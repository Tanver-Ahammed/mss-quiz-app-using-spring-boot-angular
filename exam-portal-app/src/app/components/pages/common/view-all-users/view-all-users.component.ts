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

  username: string = '';
  allUsers: any = [];
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
  tempUsers: any = [];

  constructor(private userService: UserService,
              private router: Router) {
  }

  displayedColumns: string[] = ['user-id', 'full-name', 'user-name', 'user-email', 'user-role', 'quiz-result'];

  ngOnInit(): void {
    this.userService.fetchAllUsers().subscribe(
      (data: any) => {
        this.users = data;
        this.allUsers = data;
      },
      (error) => {
        Swal.fire("Error!!", "Error in fetching user!!", 'error');
      }
    )
  }

  filterUsers() {
    for (let i = 0; i < this.allUsers.length; i++) {
      let fullName = this.allUsers[i].firstName.concat(' ').concat(this.allUsers[i].lastName);
      if (fullName.includes(this.username)) {
        this.tempUsers = this.tempUsers.concat(this.allUsers[i]);
      }
    }
    this.users = this.tempUsers;
    this.tempUsers = [];
    return this.users;
  }
}
