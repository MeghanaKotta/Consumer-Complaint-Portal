import { Component, OnInit } from '@angular/core';


import { AbstractControl, FormBuilder, FormControl, FormGroup, FormGroupDirective, ValidatorFn, Validators } from '@angular/forms';

import { TicketManagementService } from 'src/app/services/ticket-management.service';
import { CustomValidators } from './customvalidators';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  changeTitle = 'ChangePassword'
  hideo: boolean = true;
  hiden: boolean = true;
  hidec: boolean = true;
  changeForm: FormGroup = this.fb.group({
    newpassword: ['', [Validators.required,Validators.minLength(6)]],
    cpassword: ['', [Validators.required]]
  }, {
    validator: CustomValidators.mustMatch('newpassword', 'cpassword')
  })

  public changePwdForm: FormGroup | any;
  errorMsg:string = '';
  
  constructor(private tcktMangService:TicketManagementService, private fb: FormBuilder) {
    this.changePwdForm = new FormGroup({
      newpwd: new FormControl('', [Validators.required]),
      confirmpwd: new FormControl('', [Validators.required])
    },
    { validators:this.passwordMatch('newpwd', 'confirmpwd')});

  }

  passwordMatch(password: string, confirmPassword: string):ValidatorFn {
    return (formGroup: AbstractControl):{ [key: string]: any } | null => {
      const newpwd = formGroup.get(password);
      const confirmpwd = formGroup.get(confirmPassword);
      
      if (!newpwd || !confirmpwd) {
        return null;
      }

      if (
        confirmpwd.errors &&
        !confirmpwd.errors['passwordMismatch']
      ) {
        return null;
      }

      if (newpwd.value !== confirmpwd.value) {
        confirmpwd.setErrors({ passwordMismatch: true });
        return { passwordMismatch: true }
      } else {
        confirmpwd.setErrors(null);
        return null;
      }
    };
  }


  ngOnInit(): void {
  }

  // onSubmit(formDirective: FormGroupDirective) {
  //   if (!this.changePwdForm.valid) {
  //     return;
  //   }
  //   let formData = this.changePwdForm.value;
  //   if (formData) {
  //     this.tcktMangService.changepwd(formData.newpwd).subscribe((response) => {
  //       if(response.success){
  //         this.tcktMangService.doClientLogout()
  //       }
  //     })

  //   }
  // }

  handleKeyUp(e) {
    if (e.keyCode === 13) {
      this.confirm();
    }
  }

  get f() {
    return this.changeForm.controls;
  }

  confirm() {
    if (!this.changePwdForm.valid) {
      return;
    }
    let formData = this.changePwdForm.value;
    if (formData) {
      this.tcktMangService.changepwd(this.changeForm.value.newpassword).subscribe((response) => {
        if(response.success){
          this.tcktMangService.doClientLogout()
        }
      })

    }
  }


}
