import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public loginStatusSubject = new Subject<boolean>();

  constructor(private http: HttpClient) {
  }

  // current user which is login
  public getCurrentUser() {
    return this.http.get(`${baseUrl}/current-user`);
  }

  // generate token
  public generateToken(loginData: any) {
    console.log(loginData)
    return this.http.post(`${baseUrl}/generate-token`, loginData);
  }

  // login user: set token token to local storage
  public loginUserForSetToken(token: string) {
    localStorage.setItem("token", token);
    return true;
  }

  // isLoggedIn: user is login or not
  public isLoggedIn() {
    let token = localStorage.getItem("token");
    if (token == undefined || token == '' || token == null)
      return false;
    else
      return true;
  }

  // logout : remove token from local storage
  public logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }

  // get token
  public getToken() {
    return localStorage.getItem("token");
  }

  // set user details
  public setUserDetails(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
    return true;
  }

  // get user details
  public getUserDetails() {
    let userDetails = localStorage.getItem('user');
    if (userDetails != null)
      return JSON.parse(userDetails);
    else {
      this.logout();
      return null;
    }
  }

  // get user role
  public getUserRole() {
    let user = this.getUserDetails();
    return user.roleDTOS[0].roleName;
  }

}
