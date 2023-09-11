import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TaskService } from '../services/task.service';

@Component({
  selector: 'app-task-forms',
  templateUrl: './task-forms.component.html',
  styleUrls: ['./task-forms.component.css']
})
export class TaskFormsComponent implements OnInit {

  taskParameters: FormGroup | any;

  invalidFields = false;


  constructor(private fb: FormBuilder, private router: Router, private taskService: TaskService) { }

  ngOnInit(): void {
    this.taskParameters = this.fb.group({
      title: new FormControl('', [
        Validators.required
      ]),
      description: new FormControl('', [
        Validators.required
      ]),
      deadline: new FormControl('', [
        Validators.required
      ]),
    })
  }

  createTask(){
    if (this.taskParameters.valid) {
      this.invalidFields = false;  

      this.taskService.createTask(this.taskParameters.value).subscribe({
        next: (res) =>{
          this.router.navigate(['inicio'])
        },
        error: (err) => {
          console.log(err);
        }
      })
    }else{
      this.invalidFields = true;
    }
  }

}
