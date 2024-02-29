import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../../user-services/question-service/question.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  questions:any[]=[];
  pageNum:number=0;
  total!:number;

  constructor(private service:QuestionService) { }

  ngOnInit(): void {
    this.getAllQuestions();
  }

  getAllQuestions(){
    this.service.getAllQuestion(this.pageNum).subscribe((res)=>{
      console.log(res);
      this.questions=res.questionDtoList;
      this.total=res.totalpages*5;
    })
  }

  pageIndexChange(event: any){
    this.pageNum=event.pageIndex;
    if(this.getLatest){
      this.getLatestQuestions();
    }
    else{
    this.getAllQuestions();
    }
  }
getLatest:boolean=false;
  getLatestQuestions(){
    console.log("radio working");
    this.service.getLatestQuestion(this.pageNum).subscribe(
      (res)=>{
        console.log(res);
        this.questions=res.questionDtoList;
        this.total=res.totalpages*5; 
        this.getLatest=true;
      }
    )
  }

}
