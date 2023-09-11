import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbOffcanvas, OffcanvasDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { TaskService } from '../services/task.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  taskFilter: FormGroup | any;
  taskFormGroup: FormGroup | any;
  tasks: any;
  taskInfo: any;
  invalidFields = false;

  editingTask = false;

  taskProgress: any = {
    "NOT_STARTED": "Não iniciada",
    "IN_PROGRESS": "Em progresso",
    "COMPLETED": "Finalizada", 
    "ARCHIVED": "Arquivada"
  }

  constructor(private fb: FormBuilder, private offcanvasService: NgbOffcanvas, private taskService: TaskService) { }

  ngOnInit(): void {
    this.taskFilter = this.fb.group({
      progress: new FormControl('', [
        Validators.required
      ]),
    })
    
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

    

    this.getAllTasks();
  }

  async getAllTasks(){    
    if(this.taskFilter.valid){
      this.taskService.getAllTasks(this.taskFilter.value.progress).subscribe({
        next: (res) =>{
          this.tasks = res.data 
        },
        error: (err) => {
          console.log(err);
        }
      })
    }else{
      this.taskService.getAllTasks().subscribe({
        next: (res) =>{
          this.tasks = res.data 
        },
        error: (err) => {
          console.log(err);
        }
      })
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
          this.getAllTasks();
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
        this.getAllTasks();
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
