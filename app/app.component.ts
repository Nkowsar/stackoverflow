import { Component } from '@angular/core';
// import { StorageService } from './auth-services/storage-service/storage.service';
// import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  
})
export class AppComponent {
  title = 'stackoverflow-angular';

  // isUserLoggedIn:boolean | undefined;
  // constructor(private router:Router){}
  // ngOnInit(){
  //   this.updateIsUserLoggedIn();
  //   this.router.events.subscribe(event=>{
  //     if(event instanceof NavigationEnd){
  //       this.updateIsUserLoggedIn();
  //     }
  //   }
  //     )
  // }
  // private updateIsUserLoggedIn():void{
  //     this.isUserLoggedIn=StorageService.updateUserLoggedIn();
  // }

  // logout(){
  //   StorageService.logout();
  //   this.router.navigateByUrl("/auth/login");
  // }
  
}
