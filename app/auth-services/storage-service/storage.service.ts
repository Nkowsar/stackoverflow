import { Injectable } from '@angular/core';

const TOKEN='c_token';
const USER='c_user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  static hasToken():boolean{
    if(this.getToken===null){
      return false;
    }
    return true;
  }
  public saveUser(user: any) {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER,JSON.stringify(user));
  }
  public saveToken(token:string){
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN,token);
  }

  static getToken(){
    return localStorage.getItem(TOKEN);
  }
  static getUserId():number{
    const user=this.getUser();
    if(user==null)
    {
      return 1;
    }
    console.log(user);
    console.log(JSON.parse(user).userid);
    return JSON.parse(user).userid;
  }
  static getUser():any{

    return localStorage.getItem(USER);

  }
  static updateUserLoggedIn():boolean{
      if(this.getToken()==null){
        return false;
      }
      return true;
  }

  static logout(){
      window.localStorage.removeItem(TOKEN);
      window.localStorage.removeItem(USER);
  }

  constructor() { }
}
