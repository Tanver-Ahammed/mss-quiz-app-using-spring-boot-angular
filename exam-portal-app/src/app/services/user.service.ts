import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  // add user
  public addUser(user: any) {
    return this.http.post(`${baseUrl}/user/registration`, user);
  }

  // get all roles
  public fetchAllRoles() {
    return this.http.get(`${baseUrl}/super/admin/roles/`);
  }

  // fetch all users
  public fetchAllUsers() {
    return this.http.get(`${baseUrl}/user/all`);
  }

  // fetch user by id
  public fetchUserById(userId: number) {
    return this.http.get(`${baseUrl}/user/id/${userId}`);
  }

  // update user
  public updateUser(user: any) {
    return this.http.put(`${baseUrl}/user/`, user);
  }

  // update user role by super admin
  public updateUserRole(user: any) {
    return this.http.put(`${baseUrl}/super/admin/update/`, user);
  }


}
