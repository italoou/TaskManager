import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginFG: FormGroup | any;

  campoInvalido: boolean = false;

  constructor(private fb: FormBuilder, 
    private router: Router, 
    private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    if(this.authenticationService.isLoggedIn){
      
      this.goToMyTask()
      
    }

    this.loginFG = this.fb.group({
      username: new FormControl('', [
        Validators.required
      ]),
      password: new FormControl('', [
        Validators.required
      ])
    })
  }

  async login(){

    if(this.loginFG.valid){
      this.campoInvalido = false;

      this.authenticationService.login({
        username: this.loginFG.value.username,
        password: this.loginFG.value.password
      }).subscribe({
        next: (res) =>{

          this.goToMyTask()

        }, 
        error: (err) => {
          let message = "Erro ao autenticar."
          
          let causes: any = {
            "NO_PASSWORD": "Senha não foi informada.",
            "USER_DISABLED": "Usuário desativado.",
            "INVALID_CREDENTIALS": "Credenciais inválidas",
          }

          if(Object.getOwnPropertyNames(err.error)){
            message = causes[err.error];
          }
        }});
    }else{
      this.campoInvalido = true
    }
  }

  goToSignUp(){
    this.router.navigate(['/cadastrar'])
  }

  goToMyTask(){
    this.router.navigate(['/inicio'])
  }

}
