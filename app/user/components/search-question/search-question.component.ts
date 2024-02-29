import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuestionService } from '../../user-services/question-service/question.service';

@Component({
  selector: 'app-search-question',
  templateUrl: './search-question.component.html',
  styleUrls: ['./search-question.component.scss']
})
export class SearchQuestionComponent implements OnInit {

  titleForm!:FormGroup;
pageNum:number=0;
total!:number;
questions:any[]=[];
  constructor(
    private questionService:QuestionService,
    private fb:FormBuilder
    ) { }

  ngOnInit(): void {
    this.titleForm=this.fb.group(
      {
        title:[null,Validators.required]
      }
    )
  }

  searchQuestionByTitle(){
    console.log(this.titleForm.value);
    this.questionService.searchQuestionByTitle(this.titleForm.controls['title']!.value,this.pageNum).subscribe(
      (res)=>{
        console.log(res);
        this.questions=res.questionDtoList;
        this.total=res.totalpages*5;
      }
    )

  }
  pageIndexChange(event:any){
    this.pageNum=event.pageIndex;
    

  }

}
