import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../../../services/category.service";
import Swal from "sweetalert2";
import {LoginService} from "../../../../services/login.service";

@Component({
  selector: 'app-user-sidebar',
  templateUrl: './user-sidebar.component.html',
  styleUrls: ['./user-sidebar.component.css']
})
export class UserSidebarComponent implements OnInit {

  categories: any = [];

  constructor(private categoryService: CategoryService,
              private loginService: LoginService) {
  }

  ngOnInit(): void {

    this.categoryService.fetchAllCategories().subscribe(
      (data: any) => this.categories = data,
      error => Swal.fire('Error!!', 'error in loading category...', 'error')
    );

  }

  // logout method
  logout() {
    this.loginService.logout();
    window.location.reload();
  }

}
