import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-usuario-forms',
  templateUrl: './usuario-forms.component.html',
  styleUrls: ['./usuario-forms.component.css']
})
export class UsuarioFormsComponent implements OnInit {

  userFG: FormGroup | any;
  invalidFields: boolean = false;

  constructor(private fb: FormBuilder, 
    private router: Router,
    private userService: UserService) { }

  ngOnInit(): void {

    this.userFG = this.fb.group({
      name: new FormControl('', [
        Validators.required
      ]),
      username: new FormControl('', [
        Validators.required
      ]),
      password: new FormControl('', [
        Validators.required
      ])
    })
  }

  signUp(){

    if(this.userFG.valid){
      this.invalidFields = false;

      this.userService.signUp({
        name: this.userFG.value.name,
        username: this.userFG.value.username,
        password: this.userFG.value.password
      }).subscribe({
        next: (res) =>{

          this.goToLogin();

        }, 
        error: (err) => {

          console.log(err);
          
        }});
    }else{
      this.invalidFields = true
    }

  }

  goToLogin() {
    this.router.navigate([""])
  }
}
