import { Component, OnInit, inject } from '@angular/core';
import { QuestionService } from '../../user-services/question-service/question.service';
//import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatChipInputEvent } from '@angular/material/chips';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {COMMA,ENTER} from '@angular/cdk/keycodes';

export interface Tags {
  name: string;
}


@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.scss']
})
export class PostQuestionComponent implements OnInit {

 // tags1:Tags[]=[];
  //tags:{name:string}[]=[];
  addOnBlur=true;
  selectable = true;
  removable = true;
  isSubmitting:boolean | undefined;
  

  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
    //announcer=inject(LiveAnnouncer);
  add(event: MatChipInputEvent):void{
    const value=(event.value).trim();
    
    if(value){
      //this.tags.push({name:value});
      const control=new FormControl(value);
      (<FormArray>this.validateForm.get('tags')).push(control);
    }
    event.chipInput!.clear();
    
  }

  // remove(tag: any):void{
  //   const index=this.tags.indexOf(tag);
  //   if(index>=0){
  //     this.tags.splice(index,1);
  //     //this.announcer.announce('Removed ${tag}');
  //   }
  // }
  remove(id:number){
    (<FormArray>this.validateForm.get('tags')).removeAt(id);

  }

  
  constructor(private service:QuestionService,private fb:FormBuilder) { }
 
  ngOnInit(): void {
      // this.validateForm=this.fb.group({
      //   title:['',Validators.required],
      //   body:['',Validators.required],
      //   tags:this.fb.array([])

      // })
  }
  validateForm=new FormGroup({
    title: new FormControl("",[Validators.required]),
    body: new FormControl("",[Validators.required]),
    tags: new FormArray([])
  });

  get title(): FormControl{
    return this.validateForm.get("title") as FormControl;
  }
  get body(): FormControl{
    return this.validateForm.get("body") as FormControl;
  }
  get tags(){
    return  (<FormArray>this.validateForm.get('tags')).controls;
  }

  postQuestion(){
      console.log(this.validateForm.value);
      this.service.postQuestion(this.validateForm.value).subscribe(
        (res)=>{
        console.log(res);
        if(res.id!=null){
          alert("question posted successfully");
        }
        else{
          alert("something went wrong");
        }
      })
  }

}
