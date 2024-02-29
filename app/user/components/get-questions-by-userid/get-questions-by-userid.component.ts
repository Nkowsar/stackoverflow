import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../../user-services/question-service/question.service';

@Component({
  selector: 'app-get-questions-by-userid',
  templateUrl: './get-questions-by-userid.component.html',
  styleUrls: ['./get-questions-by-userid.component.scss']
})
export class GetQuestionsByUseridComponent implements OnInit {

  questions:any[]=[];
  pageNum:number=1;
  total!:number;

  constructor(private service:QuestionService) { }

  ngOnInit(): void {
    this.getAllQuestions();
  }

  getAllQuestions(){
    this.service.getQuestionsByUserId(this.pageNum).subscribe((res)=>{
      console.log(res);
      this.questions=res.questionDtoList;
      this.total=res.totalpages*5;
    })
  }

  pageIndexChange(event: any){
    this.pageNum=event.pageIndex;
    this.getAllQuestions();
  }

}
