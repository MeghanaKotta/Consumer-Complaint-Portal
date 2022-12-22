import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder, ValidatorFn, AbstractControl, ValidationErrors} from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorMessage, TicketManagementService } from 'src/app/services/ticket-management.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  regisrationForm: FormGroup | any;
  today = new Date();
  errorMsg:string = ''
  maxDob: Date;

  constructor(private _ticketManagmentService : TicketManagementService,
    private formbuilder: FormBuilder, private router:Router) {
    this.regisrationForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email, Validators.pattern(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
      ),]),
      password: new FormControl('', [Validators.required, Validators.pattern(
        '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$'
      )]),
      username: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required]),
      isManager: new FormControl(false),
      dateOfBirth: new FormControl('', [Validators.required]),
      managerUsername: new FormControl(''),
      managerKey: new FormControl('')
    },

    { validators:this.passwordMatch('password', 'confirmPassword')});
    this.errorMsg = '';
    this._ticketManagmentService.errorMsgSub.subscribe((errorInfo:any) => {
      if(errorInfo.errorType == ErrorMessage.Registration_Error){
        this.errorMsg = errorInfo.msg;
      }
    })

    const today = new Date();
    this.maxDob = new Date(
      today.getFullYear() - 18,
      today.getMonth(),
      today.getDate()
    );

  }
  passwordMatch(password: string, confirmPassword: string):ValidatorFn {
    return (formGroup: AbstractControl):{ [key: string]: any } | null => {
      const passwordControl = formGroup.get(password);
      const confirmPasswordControl = formGroup.get(confirmPassword);
      
      if (!passwordControl || !confirmPasswordControl) {
        return null;
      }

      if (
        confirmPasswordControl.errors &&
        !confirmPasswordControl.errors['passwordMismatch']
      ) {
        return null;
      }

      if (passwordControl.value !== confirmPasswordControl.value) {
        confirmPasswordControl.setErrors({ passwordMismatch: true });
        return { passwordMismatch: true }
      } else {
        confirmPasswordControl.setErrors(null);
        return null;
      }
    };
  }

  onManagerCheck(e: any) {
    this.regisrationForm.controls.managerUsername.setValidators(e.checked ? [Validators.required] : null);
    this.regisrationForm.controls.managerKey.setValidators(e.checked ? [Validators.required] : null);
    this.regisrationForm.controls.managerKey.updateValueAndValidity();
    this.regisrationForm.controls.managerUsername.updateValueAndValidity();
  }

  showManagerFields() {
    return this.regisrationForm.value.isManager;
  }
  ngOnInit(): void {
  }

  switchPage(page:any){
    this.router.navigate(['/'+page],{});
  }

  formattedDate(date: Date): string {
    if(date){
      return date.toISOString().split('T')[0]
    }
    return '';
  }

  onSubmit() {
    if (!this.regisrationForm.valid) {
      return;
    }
    let formData = this.regisrationForm.value;
    if (formData) {
      let registrationObj: any = {
        "isManager": formData.isManager,
        "isOrgMember": false,
        "userDetailsDTO": {
          "username": formData.username,
          "password": formData.password,
          "roleid": null,
          "dob": this.formattedDate(formData.dateOfBirth),
          "emailid": formData.email,
          "createdby": null

        }
      }
      if (registrationObj.isManager) {
        registrationObj["managerAuthDTO"] = {
          "username": formData.managerUsername,
          "key": formData.managerKey
        }
      }
      this._ticketManagmentService.userRegister(registrationObj);

    }
  }
}
