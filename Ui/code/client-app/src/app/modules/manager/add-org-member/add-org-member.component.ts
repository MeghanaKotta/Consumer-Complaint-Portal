import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { ErrorMessage, TicketManagementService } from 'src/app/services/ticket-management.service';

@Component({
  selector: 'app-add-org-member',
  templateUrl: './add-org-member.component.html',
  styleUrls: ['./add-org-member.component.scss']
})
export class AddOrgMemberComponent implements OnInit {

  addMemeberForm: FormGroup | any;
  today = new Date();
  departments: any[] = [
    {value: 'Dept-1', viewValue: 'Food'},
    {value: 'Dept-2', viewValue: 'Cloths'},
    {value: 'Dept-3', viewValue: 'Medicine'},
  ];

  organizations: any [] = [
    {value: 'Org-1', viewValue: 'Org 1'},
    {value: 'Org-2', viewValue: 'Org 2'},
    {value: 'Org-3', viewValue: 'Org 3'},
  ];
  public selectedDept:string = '';
  public selectedOrg:string = '';
  public depOrgMapping:any;
  public errorMsg:string = ''


  constructor(private tcktMangService:TicketManagementService) {
    this.addMemeberForm = new FormGroup({
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
      managerKey: new FormControl(''),
      deptFormCtrl: new FormControl('', [Validators.required]),
      orgFormCtrl: new FormControl('', [Validators.required])
    },

    { validators:this.passwordMatch('password', 'confirmPassword')});

    this.tcktMangService.getDepOrgMappingOnUserId().subscribe((response) => {
      if(response.success){
        this.depOrgMapping = response.data;
        if(this.depOrgMapping && Object.keys(this.depOrgMapping).length > 0){
          this.departments = Object.keys(this.depOrgMapping).map((key) => {
            return {
              value:this.depOrgMapping[key].id,
              viewValue:this.depOrgMapping[key].depname
            }
          });
          this.onDeptChange(null);
        }
      }
    });
    this.errorMsg = '';
    this.tcktMangService.errorMsgSub.subscribe((errorInfo:any) => {
      if(errorInfo.errorType == ErrorMessage.Registration_Error){
        this.errorMsg = errorInfo.msg;
      }
    })

  }

  onDeptChange($evt:any): void {
    if(this.selectedDept){
      this.organizations = [];
      this.depOrgMapping[this.selectedDept].orgsList.map((org:any) => {
        this.organizations.push({
          value:org.id,
          viewValue:org.orgname
        })
      });
      this.selectedOrg = ''
    }
  }


  ngOnInit(): void {
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

  formattedDate(date: Date): string {
    if(date){
      return date.toISOString().split('T')[0]
    }
    return '';
  }
  
  onSubmit() {
    const userid = this.tcktMangService.appState.userdetails.userid
    if (!this.addMemeberForm.valid) {
      return;
    }
    let formData = this.addMemeberForm.value;
    if (formData) {
      let registrationObj: any = {
        "isManager": false,
        "isOrgMember": true,
        "managerUserid":userid,
        "userDetailsDTO": {
          "username": formData.username,
          "password": formData.password,
          "roleid": null,
          "dob": this.formattedDate(formData.dateOfBirth),
          "emailid": formData.email,
          "createdby": null
        },
        "orgMemberOrgId": this.selectedOrg,
        "orgMemberDepId": this.selectedDept,
      }
      this.tcktMangService.userRegister(registrationObj);

    }
  }
}
