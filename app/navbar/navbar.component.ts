import { Component, OnInit } from '@angular/core';

import { NavigationEnd, Router } from '@angular/router';
import { StorageService } from '../auth-services/storage-service/storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isUserLoggedIn:boolean | undefined;
  constructor(private router:Router, private storageService:StorageService){}
  ngOnInit(){
    this.updateIsUserLoggedIn();
    this.router.events.subscribe(event=>{
      if(event instanceof NavigationEnd){
        this.updateIsUserLoggedIn();
      }
    }
      )
  }
  private updateIsUserLoggedIn():void{
      this.isUserLoggedIn=StorageService.updateUserLoggedIn();
  }

  logout(){
    StorageService.logout();
    this.router.navigateByUrl("/auth/login");
  }
  

}
