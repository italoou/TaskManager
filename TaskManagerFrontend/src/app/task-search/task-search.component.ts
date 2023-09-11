import { Component, OnInit, TemplateRef } from '@angular/core';
import { TaskService } from '../services/task.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-task-search',
  templateUrl: './task-search.component.html',
  styleUrls: ['./task-search.component.css']
})
export class TaskSearchComponent implements OnInit {

  taskSearchText: any;
  searchResult = false;

  tasks: any;
  taskInfo: any;
  taskFormGroup: FormGroup | any;
  editingTask = false;
  invalidFields = false;

  taskProgress: any = {
    "NOT_STARTED": "Não iniciada",
    "IN_PROGRESS": "Em progresso",
    "COMPLETED": "Finalizada", 
    "ARCHIVED": "Arquivada"
  }

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private taskService: TaskService, private fb: FormBuilder, private offcanvasService: NgbOffcanvas) { }

  ngOnInit(): void {
    this.taskFormGroup = this.fb.group({
      title:new FormControl('', [
        Validators.required
      ]),
      description:new FormControl('', [
        Validators.required
      ]),
      deadline: new FormControl('', [
        Validators.required
      ]),
      progress: new FormControl('', [
        Validators.required
      ]),
    })

    this.activatedRoute.queryParams.subscribe(params => {
      this.taskSearchText = params['texto'];
    }); 

    if(this.taskSearchText == null){
      this.router.navigate(["/"])
    }

    this.search()
  }

  search(){
    console.log(this.taskSearchText);
    
    if(this.taskSearchText != null){

      this.taskService.getAllTasksWithText(this.taskSearchText).subscribe({
        next: (res) =>{
          if(res.data.length == 0 ){
            this.searchResult = false
          }
          else{
            this.searchResult = true
            this.tasks = res.data 
          }
        },
        error: (err) => {
          console.log(err);
        }
      })
    } else {
      this.searchResult = false;
    }
  }
  async updateTask(){

    if (this.taskFormGroup.valid) {
      this.invalidFields = false;

      this.taskInfo.title = this.taskFormGroup.value.title
      this.taskInfo.description = this.taskFormGroup.value.description
      this.taskInfo.deadline = this.taskFormGroup.value.deadline
      this.taskInfo.status = this.taskFormGroup.value.progress

      this.taskService.updateTask(this.taskInfo).subscribe({
        next: (res) =>{
          this.search();
        },
        error: (err) => {
          console.log(err);
        }
      })
    }else{
      this.invalidFields = true;
    }
  }

  updateTaskProgress(progress: string, taskId: number){
    this.taskService.updateTaskProgress(progress, taskId).subscribe({
      next: (res) =>{
        this.search();
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  setTaskInfo(task: any){
    this.taskFormGroup = this.fb.group({
      title:new FormControl(task.title, [
        Validators.required
      ]),
      description:new FormControl(task.description, [
        Validators.required
      ]),
      deadline: new FormControl(task.deadline, [
        Validators.required
      ]),
      progress: new FormControl(task.status, [
        Validators.required
      ]),
    })

    console.log(this.taskFormGroup);
    
  }

  makeEditableTask(){
    this.editingTask = true;
    this.setTaskInfo(this.taskInfo)
  }

  cancelEditing(){
    this.editingTask = false;
  }

  openEnd(content: TemplateRef<any>, task: any) {
    this.taskInfo = task;
		this.offcanvasService.open(content, { position: 'end' })
    .result.finally(
      () =>{
        this.cancelEditing()
      });
	}

  formatViewerDate(date: Date | string) {
    
    let data = new Date(date)

    return [
      this.padTo2Digits(data.getDate()),
      this.padTo2Digits(data.getMonth() + 1),
      data.getFullYear(),
    ].join("/");
  }

  padTo2Digits(num: number) {
    return num.toString().padStart(2, "0");
  }

}
