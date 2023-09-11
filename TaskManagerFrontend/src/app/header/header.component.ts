import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  searchFormGroup: FormGroup | any;

  public isCollapsed = true;
  public isSearchCollapsed = true;

  public screenWidth: any;

  constructor(private router: Router, private authenticationService: AuthenticationService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      text:new FormControl('', [
        Validators.required
      ]),
    })
    this.screenWidth = window.innerWidth;
    
    if(this.screenWidth < 576){
      this.isCollapsed = true;
    }
  }

  goToSearch(){
    this.router.navigate(['/buscar'], {queryParams: {texto: this.searchFormGroup.value.text}});
  }

  goToMyTask(){
    this.router.navigate(['inicio'])
  }

  goToAddTask(){
    this.router.navigate(['adicionar'])
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(['login']);
  }
}
