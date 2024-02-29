import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PostQuestionComponent } from './components/post-question/post-question.component';
import { ViewQuestionComponent } from './components/view-question/view-question.component';
import { GetQuestionsByUseridComponent } from './components/get-questions-by-userid/get-questions-by-userid.component';
import { SearchQuestionComponent } from './components/search-question/search-question.component';

const routes: Routes = [
  {path:'dashboard',component:DashboardComponent},
  {path:'question',component:PostQuestionComponent},
  {path:'question/:questionId',component:ViewQuestionComponent},
  {path:'my_questions',component:GetQuestionsByUseridComponent},
  {path:'search_question',component:SearchQuestionComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
