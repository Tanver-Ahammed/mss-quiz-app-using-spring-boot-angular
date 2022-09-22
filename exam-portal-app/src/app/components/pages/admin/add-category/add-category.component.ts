import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../../../services/category.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
// @ts-ignore
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  public Editor = ClassicEditor;

  category = {
    title: '',
    description: ''
  }


  constructor(private categoryService: CategoryService,
              private snack: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  categorySubmit() {
    console.log(this.category)
    if (this.category.title.trim() == '' || this.category.title == null) {
      this.snack.open('user name is required!!', 'ok', {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      return;
    }

    // all done
    this.categoryService.addCategory(this.category).subscribe(
      (data: any) => {
        Swal.fire('Success!!', 'Category is Successfully Added...', 'success');
        this.router.navigateByUrl('/admin/categories');
      }, (error: any) => {
        Swal.fire('Error!!', 'Server Error!!! Try Again...', 'error');
      }
    )


  }

}
