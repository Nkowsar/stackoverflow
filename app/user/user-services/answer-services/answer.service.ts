import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth-services/storage-service/storage.service';
const BASIC_URL=["http://localhost:8080/"];

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(private http:HttpClient) { }

  postAnswer(answerDto: any): Observable<any>{
    
    console.log(answerDto);
    return this.http.post<[]>(BASIC_URL+"api/answer",answerDto,
    {
      headers:this.createAuthorizationHeader()
    }
    );
  }

  postCommentToAnswer(commentDto: any): Observable<any>{
    
    console.log(commentDto);
    return this.http.post<[]>(BASIC_URL+"api/answer/comment",commentDto,
    {
      headers:this.createAuthorizationHeader()
    }
    );
  }

  postAnswerImage(file: any,answerId:number): Observable<any>{
    
    
    return this.http.post(BASIC_URL+`api/image/${answerId}`,file,
    {
      headers:this.createAuthorizationHeader()
    }
    );
  }

approveAnswer(answerId:number): Observable<any>{
    return this.http.get(BASIC_URL+`api/answer/answerapprove/${answerId}`,
    {
      headers:this.createAuthorizationHeader()
    }
    );
  }

  addVoteToAnswer(answerVoteDto:any): Observable<any>{
    return this.http.post(BASIC_URL+"api/answer-vote",answerVoteDto,
    {
      headers:this.createAuthorizationHeader()
    }
    );
  }

  createAuthorizationHeader():HttpHeaders{
    let authHeaders: HttpHeaders=new HttpHeaders();
    return authHeaders.set(
      "Authorization","Bearer " + StorageService.getToken()
    );
  }



}
