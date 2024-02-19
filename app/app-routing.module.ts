import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingpageComponent } from './landingpage/landingpage.component';


const routes: Routes = [
  { path: 'auth', 
  loadChildren: () => import('./authentication/authentication.module').then(m => m.AuthenticationModule) },
  {path:'',component:LandingpageComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
