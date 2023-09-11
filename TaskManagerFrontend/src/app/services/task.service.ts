import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private url = 'http://localhost:8030/api/taskmanager/tasks'

  constructor(private http: HttpClient) { }

  getAllTasks(progress?: String){
    let url = this.url;
    
    if(progress){
      url = this.url+"?progress="+progress;
    }

    const res =  this.http.get<any>(url).pipe(
      map((res) => {
        return res;
      })
    );

    return res;
  }

  getAllTasksWithText(searchTerm: String){
    let url = this.url+"/search/"+searchTerm;


    const res =  this.http.get<any>(url).pipe(
      map((res) => {
        return res;
      })
    );

    return res;
  }

  createTask(task: any){
    let url = this.url;
    
    const res =  this.http.post<any>(url, task).pipe(
      map((res) => {
        return res;
      })
    );

    return res;
  }

  updateTask(task: any){
    let url = this.url+"/"+task.id;
    
    const res =  this.http.put<any>(url, task).pipe(
      map((res) => {
        return res;
      })
    );

    return res;
  }

  updateTaskProgress(progress: string, taskId: number){
    let url = this.url+"/progress/"+taskId;
    
    const res =  this.http.put<any>(url, {progress: progress}).pipe(
      map((res) => {
        return res;
      })
    );

    return res;
  }
}
