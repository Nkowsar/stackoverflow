import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../../user-services/question-service/question.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from 'src/app/auth-services/storage-service/storage.service';
import { AnswerService } from '../../user-services/answer-services/answer.service';

@Component({
  selector: 'app-view-question',
  templateUrl: './view-question.component.html',
  styleUrls: ['./view-question.component.scss']
})
export class ViewQuestionComponent implements OnInit {

  selectedFile!:File | null;
  imagePreview!: string | ArrayBuffer | null;
  formData:FormData=new FormData();
  answers:any[]=[];
  displayButton:boolean=false;
  questionId:number=this.activateRoute.snapshot.params["questionId"];
  question:any;
  validateForm!: FormGroup;
  constructor(
    private questionService:QuestionService,
    private activateRoute:ActivatedRoute,
    private fb:FormBuilder,
    private answerService:AnswerService
    ) { }

  ngOnInit(): void {
    this.validateForm=this.fb.group({
      body:[null,Validators.required]
    })
    this.getQuestionById();
  }

  getQuestionById(){
    this.questionService.getQuestionById(this.questionId).subscribe(
      (res)=>{
        console.log(res);
        this.question=res.questionDto;
        res.answerDtoList.forEach((element:any) => {
          if(element.file!=null){
            element.convertedImg='data:image/jpeg;base64,'+ element.file.data;
          }
          this.answers.push(element);
        });
        
        if(StorageService.getUserId()==this.question.userid){
          
          this.displayButton=true;
        }
      }
    )
  }

  approveAnswer(answerId:number){
    this.answerService.approveAnswer(answerId).subscribe(
      (res)=>{
        console.log(res);
        if(res.id!=null){
          alert("answer Approved Successfully");
          this.getQuestionById();
        }
      }
    )
  }

  postComment(answerid:number,comment:string){
    const commentDto={
      body:comment,
      answerId:answerid,
      userId:StorageService.getUserId()

    }
    console.log(commentDto);
    this.answerService.postCommentToAnswer(commentDto).subscribe(
      (res)=>{
        if(res.id!=null){
          console.log("commented successfully ",res);
          this.answers=[];
          this.getQuestionById();
          alert("commented successfully");
        }
        else{
          alert("something went wrong");
        }
        
      }
    )
  }

  addAnswer(){
    console.log(this.validateForm.value);
    const data=this.validateForm.value;
    data.questionId=this.questionId;
    data.userId=StorageService.getUserId();
    console.log(data);
    if(this.selectedFile!=null){
    this.formData.append("multipartFile",this.selectedFile);
    }
    this.answerService.postAnswer(data).subscribe(
      (res)=>{
        
        this.answerService.postAnswerImage(this.formData,res.id).subscribe(
          (res)=>{
            console.log("image response",res);
          }
        )
        console.log("answer response",res);
        this.answers=[];
        this.getQuestionById();
        //add toastr
      }
    )
  }

  onFileSelected(event: any){
    this.selectedFile=event.target.files[0];
    this.priviewImage();

  }

  priviewImage(){
    const reader=new FileReader();
    reader.onload=()=>{
      this.imagePreview=reader.result;

    };
    if(this.selectedFile!=null){
    reader.readAsDataURL(this.selectedFile);
    }
  }

  addVote(voteType:string,voted:number){
    if(voted==1 || voted==-1){
      alert("already voted");
    }
    else{
      console.log(voteType);
      const data={
        voteType:voteType,
        questionId:this.questionId,
        userId:StorageService.getUserId()
  
      }
      this.questionService.addVoteToQuestion(data).subscribe(
        (res)=>{
          console.log(res);
          this.answers=[];
          this.getQuestionById();
        }
      )
    }
    
  }

  addVoteToAnswer(type:string,answerId:number,voted:number){
    if(voted==1 || voted==-1){
      alert("already voted")
    }
    else{
      console.log("type of vote: ",type);
    console.log("answer Id: ",answerId);
    const answerVoteDto={
      voteType: type,
      userId: StorageService.getUserId(),
      answerId:answerId
    }
    this.answerService.addVoteToAnswer(answerVoteDto).subscribe(
      (res)=>{
        console.log("answer vote added ",res);
        if(res.id!=null){
          alert("vote added auccessfully");
        }
        else{
          alert("something went wrong");
        }
        this.answers=[];
        this.getQuestionById();
        
      }
    )
    }
    
  }
}
