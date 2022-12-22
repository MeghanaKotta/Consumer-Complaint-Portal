import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AlertService } from 'src/app/services/alert.service';
import { TicketManagementService } from 'src/app/services/ticket-management.service';

@Component({
  selector: 'app-join-organization',
  templateUrl: './join-organization.component.html',
  styleUrls: ['./join-organization.component.scss']
})
export class JoinOrganizationComponent implements OnInit {

  public depOrgMapping:any;

  public departments: any[]= [];
  public selectedDept:string = '';
  public selectedOrg:string = '';
  public organizations: any [] = [];
  public managersList: any [] = [];

  constructor(private tcktMangService:TicketManagementService,private alertSer:AlertService) {
    this.getEligibleDeptAndOrg();
  }

  getEligibleDeptAndOrg():void {
    this.tcktMangService.getManagerEligibleJoiningDepOrg().subscribe((response) => {
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

  onSubmit() {
    if(this.selectedOrg && this.selectedDept){
      const userid = this.tcktMangService.appState.userdetails.userid;
      const payLoad = {
        "userid":userid,
        "depid":this.selectedDept,
        "orgid":this.selectedOrg
      }
      //getManagerEligibleJoiningDepOrg
      this.tcktMangService.joinManagerInDepOrg(payLoad).subscribe((response:any) => {
        const msg = 'you are added to the Department '+this.selectedDept +' and organization ' +this.selectedOrg;
        this.selectedDept = '';
        this.selectedOrg = '';
        this.getEligibleDeptAndOrg();
        // alert(msg);
        this.alertSer.successAlert("Joined successfully")

      })
    }
    else
    {
      
      this.alertSer.successAlert("Nothing has been selected !!")
    }
  }

}
