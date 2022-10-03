import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../../services/user.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-update-role',
  templateUrl: './update-role.component.html',
  styleUrls: ['./update-role.component.css']
})
export class UpdateRoleComponent implements OnInit {

  userId: number = 0;
  user: any;
  roles: any = [];
  username: string = '';

  constructor(private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.userId = this.activatedRoute.snapshot.params['userId'];
    this.userService.fetchUserById(this.userId).subscribe(
      (data: any) => this.user = data,
      (error) => Swal.fire("Error!!", `Server Error don't fetch user`, 'error')
    );
    this.roles = this.userService.fetchAllRoles().subscribe(
      (data: any) => this.roles = data,
      (error) => Swal.fire("Error!!", `Server Error don't fetch user`, 'error')
    );
  }

  updateUserForm() {
    this.userService.updateUserRole(this.user).subscribe(
      (data) => {
        this.user = data;
        this.router.navigate(['/super/admin/users']);
      },
      (error) => Swal.fire("Error!!", `Server Error don't update user`, 'error')
    )
  }
}
