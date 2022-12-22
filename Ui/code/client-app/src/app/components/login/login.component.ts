import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorMessage, TicketManagementService } from 'src/app/services/ticket-management.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup | any;
  errorMsg:string = ''

  constructor(private _ticketManagmentService: TicketManagementService, private router:Router) {
    this.loginForm = new FormGroup({
      password: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required])
    });
    this.errorMsg = '';
    this._ticketManagmentService.errorMsgSub.subscribe((errorInfo:any) => {
      if(errorInfo.errorType == ErrorMessage.Login_Error){
        this.errorMsg = errorInfo.msg;
      }
    });
    if(this._ticketManagmentService.appState &&
      this._ticketManagmentService.appState.sessionkey){
        this._ticketManagmentService.checkInitialRouteChange();
    }
  }

  ngOnInit(): void {
  }

  switchPage(page:any){
    this.router.navigate(['/'+page],{});
  }

  onSubmit(formDirective: FormGroupDirective) {
    if (!this.loginForm.valid) {
      return;
    }
    let formData = this.loginForm.value;
    if (formData) {
      if (formData.username) {
        var emailRegex = new RegExp(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
        if (emailRegex.test(formData.username)) {
          formData['emailid'] = formData.username;
          formData.username = null;
        }
      }
      let loginObj: any = {
        "username": formData.username,
        "emailid": formData.emailid || null,
        "password": formData.password
      }
      this._ticketManagmentService.userLogin(loginObj);

    }
    this.loginForm.reset();
    formDirective.resetForm();
  }
}
