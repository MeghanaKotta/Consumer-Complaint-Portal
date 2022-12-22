import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { AlertService } from 'src/app/services/alert.service';
import { TicketManagementService } from 'src/app/services/ticket-management.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.scss']
})

export class CreateTicketComponent implements OnInit {
  createTicketForm: FormGroup | any;
  editorStyle = {
    height: 'calc(50vh - 95px)'
  };
  quillConfiguration={
    toolbar: [
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],
        [{ list: 'ordered' }, { list: 'bullet' }],
        // [{ header: [1, 2, 3, 4, 5, 6, false] }],
        [{ color: [] }, { background: [] }],
        ['link'],
        ['clean']
    ],
}

  depOrgMapping:any;
  titleTxt:string = '';
  selectedDept:string = '';
  selectedOrg:string = '';
  htmlContent:string = ''
  organizations: any [] = [];

  departments: any[] = [];

  tags: any[] = [];
  selectedTags:any[] = [];

  constructor(private tcktMangService:TicketManagementService,private alertSer:AlertService) {
    this.createTicketForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      editor: new FormControl('', [Validators.required]),
      tag: new FormControl('', [Validators.required]),
      department: new FormControl('', [Validators.required]),
      organization: new FormControl('', [Validators.required]),
    });
    this.tags = this.tcktMangService.appState.tagsList;
    
    this.depOrgMapping = this.tcktMangService.appState.depOrgMapping;
    this.departments = Object.keys(this.depOrgMapping).map((key) => {
      return {
        value:this.depOrgMapping[key].id,
        viewValue:this.depOrgMapping[key].depname
      }
    });
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
    }
  }


  ngOnInit(): void {
  }

  
  onSubmit(formDirective: FormGroupDirective){
    if (!this.createTicketForm.valid) {
      return;
    }
    let selectedTags = '';
    this.selectedTags.map((val) => { selectedTags = selectedTags+";"+val});
    selectedTags = selectedTags + ';';
    const { userid, roleid } = this.tcktMangService.appState.userdetails
    let formData = this.createTicketForm.value;
    if (formData) {
      let registrationObj: any = {
        "creatoruserid": userid,
        "creatorroleid": roleid,
        "orgid":this.selectedOrg,
        "depid":this.selectedDept,
        "tags":selectedTags,
        "content":this.htmlContent,
        "title":this.titleTxt
      }
      this.tcktMangService.createTicket(registrationObj).subscribe((response:any) => {
        if(response.success){
          if(response.data.success){
<<<<<<< HEAD
            // alert('Ticket created successfully');
            this.alertSer.successAlert("Ticket successfully created !!!")
=======
            this.tcktMangService.snackMessageSub.next('Ticket created successfully');
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
            this.resetCreateTctForm(formDirective);
          }else{
            this.tcktMangService.snackMessageSub.next("Problem created while ticket creation");
          }
        }else {
          this.tcktMangService.snackMessageSub.next(response.reason);
        }
      })
    }
  }

  resetCreateTctForm (formDirective):void {
    this.titleTxt = '';
    this.selectedDept = '';
    this.selectedOrg = '';
    this.htmlContent = '';
    formDirective.resetForm(); 
  }
}