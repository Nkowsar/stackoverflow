import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth-services/auth-service/auth.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  repeatPass=false;
dateAlert: string='none';
showSuccessMessage=false;

  constructor(private service:AuthService,private router:Router) { }

  ngOnInit(): void {
  }

  registerForm=new FormGroup({
    fullName: new FormControl("",[Validators.required,Validators.minLength(5),Validators.pattern("[ a-zA-Z ]*")]),
    password: new FormControl("",[Validators.required,Validators.maxLength(16),Validators.minLength(8)]),
    
    
    email: new FormControl("",[Validators.required,Validators.email]),
    retype_Password: new FormControl("",[Validators.required]),
   
    

  });

  
  registerFormSubmitted(){
    if(this.password.value==this.retype_Password.value ){
      console.log("Submitted");
      this.service.signup(this.registerForm.value).subscribe((response)=>{
        console.log(response);
        this.router.navigateByUrl("/auth/login");
      },error=>{
        console.log(error);
      })
    }
    else{
      console.log("invalid confirmation of password");
      this.repeatPass=true;
    }
    
    
  }
  
  get fullName(): FormControl{
    return this.registerForm.get("fullName") as FormControl;
  }
  get password(): FormControl{
    return this.registerForm.get("password") as FormControl;
  }
  get email(): FormControl{
    return this.registerForm.get("email") as FormControl;
  }
  
 
  
  
  get retype_Password(): FormControl{
    return this.registerForm.get("retype_Password") as FormControl;
  }

}
