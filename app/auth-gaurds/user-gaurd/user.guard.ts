import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth-services/storage-service/storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private router:Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): 
    boolean {
    if(!StorageService.hasToken()){
      StorageService.logout();
      this.router.navigateByUrl("/auth/login");
      //add toastr "you are not logged in, login first"
      return false;
    }
    return true;
  }
  
}
