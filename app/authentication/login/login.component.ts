import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth-services/auth-service/auth.service';
//import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  showSuccessMessage=false;
  constructor(private service:AuthService,private router:Router) { }

  ngOnInit(): void {
  }

  loginForm=new FormGroup({
    email: new FormControl("",[Validators.required,Validators.email]),
    password: new FormControl("",[Validators.required,Validators.maxLength(16),Validators.minLength(8)]),
    
    
    
    

  });
  loginFormSubmitted(){
    
      //console.log("logged in successfull");
      this.service.login(this.loginForm.value).subscribe(
        (response) => {
          console.log(response);
            this.router.navigateByUrl("/user/dashboard");
            //this.toastr.success("Hurray","login Successfull");
        },
        // (error) =>{
        //     this.toastr.error("error","bad credentials");
        // }
      )
      

      // this.service.login({}).subscribe(
      //   (data)=>{},
      //   (error) => {},
      //   () => {}
      // );
    
  }
  
  
  get password(): FormControl{
    return this.loginForm.get("password") as FormControl;
  }
  get email(): FormControl{
    return this.loginForm.get("email") as FormControl;
  }
  

}
