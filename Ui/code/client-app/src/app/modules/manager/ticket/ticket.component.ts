import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AlertService } from 'src/app/services/alert.service';
import { TicketManagementService } from 'src/app/services/ticket-management.service';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})

export class TicketComponent implements OnInit {
  @Output()
  onTicketAction:EventEmitter<any> = new EventEmitter<any>();

  @Input() ticketData: any;

  public headerInfo: any = {};
  public contentInfo: any;
  public footerInfo: any;

  public isManager: boolean = false;
  public assignToEnabled : boolean = false;
  public selectedUser:any;
  public tags:number [] = [];
  public ticketStatus = ['Open', 'Inprogress','Closed']


  isMoveOnEnabled:boolean = false;

  editTicketForm: FormGroup | any;

  eligibleUsers: any[] = [];

  constructor(private tcktMangService:TicketManagementService,private alertSer:AlertService) {
  }

  ngOnInit(): void {
    // this.ticketData = {"headerInfo":{"content":"Flipkart — Delieverd wrong product and refusing to return","isBookmarked":1,"ticketid":12,"title":"return","createdat":1659560401000,"tags":";1;2;3;","status":1},"contentInfo":[{"content":"Flipkart — Delieverd wrong product and refusing to return","ticketid":12,"title":"return","updatedon":"2022-08-04 02:50:19","notes":"Dummy note","assignedBy":"Prerana","workdone":null,"worked_or_workingby":"Prerana"},{"content":"Flipkart — Delieverd wrong product and refusing to return","ticketid":12,"title":"return","assignedBy":null,"updatedon":"2022-08-04 02:50:19","notes":null,"worked_or_workingby":null,"workdone":null}],"footerInfo":{"isEditable":1,"isManager":1,"moveOnFeature":1,"assigneeOption":1,"isOrgMember":0}}
    if(this.ticketData){
      this.footerInfo = this.ticketData['footerInfo'];
      this.headerInfo = this.ticketData['headerInfo'];
      if(this.headerInfo['tags']){
        const tagIds = this.headerInfo['tags'].slice(1,this.headerInfo['tags'].length-1).split(";");
        this.tags = tagIds.map((id) => "/assets/Images/tags/"+id+".png");
      }
      this.contentInfo = this.ticketData['contentInfo'];
      if(this.footerInfo){
        this.isMoveOnEnabled = this.footerInfo.moveOnFeature;
        this.assignToEnabled = (this.footerInfo['assigneeOption'] == 1);
        this.isManager = (this.footerInfo['isManager'] == 1);
      }
      const ticketid = this.ticketData?.contentInfo[0].ticketid
      if(ticketid){
        this.tcktMangService.getEligibleUserIdsListForTicketEdit(ticketid).subscribe((response) => {
          if(response.success){
            this.eligibleUsers = response.data;
          }
        })
      }
      if(!this.isMoveOnEnabled){
        this.editTicketForm = new FormGroup({});
      }else{
        this.editTicketForm = new FormGroup({
          reason: new FormControl(''),
          assignTo: new FormControl(''),
          eligibleUserCtrl: new FormControl(''),
          workDone: new FormControl('',[Validators.required]),
          action: new FormControl('',[Validators.required])
        });
      }
    }  

    if(this.isManager){
      this.editTicketForm.controls.reason.setValidators([Validators.required]);
      this.editTicketForm.controls.eligibleUserCtrl.setValidators([Validators.required]);
    }

  }

  /*
  
  */

  formatDate(utcSeconds){
    let date = new Date(utcSeconds);
    let formattedDate = ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '/' + ((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '/' + date.getFullYear()
    return formattedDate;
  }
  onSubmit(){
    if (!this.editTicketForm.valid) {
      return;
    }
    /* assignTomeEnabled - true
      "{
      ""currentassigneeuserid"":14,
      ""currentassigneeroleid"":2,
      ""assignedbyuserid"":14,
      ""assignedbyroleid"":2,
      ""ticketid"":23
  }"
  */
  /*assignTomeEnabled - false
  "{
    ""currentassigneeuserid"":16,
    ""currentassigneeroleid"":2,
    ""assignedbyuserid"":14,
    ""assignedbyroleid"":2,
    ""ticketid"":25,
    ""workdone"":""Completed refund part"",
    ""notes"":""Is refund done"",
    ""closing"":false

}"
  */
    let formData = this.editTicketForm.value;
    if (formData) {
      const { userid, roleid } = this.tcktMangService.appState.userdetails;
      let payLoad:any = {}
      payLoad.assignedbyuserid = userid;
      payLoad.assignedbyroleid = roleid;
      payLoad.ticketid = this.ticketData.contentInfo[0].ticketid;
      if(this.assignToEnabled){
        payLoad.currentassigneeuserid = userid;
        payLoad.currentassigneeroleid = roleid;
      }else{
        payLoad.currentassigneeuserid = this.selectedUser;
        payLoad.currentassigneeroleid = this.eligibleUsers.filter((user) => user.userid == this.selectedUser)[0].roleid;
      }
      if(this.isMoveOnEnabled){
        payLoad.workdone = formData.workDone;
        payLoad.notes = formData.reason;
        payLoad.closing = formData.action == 'move' ? false : true;
      }
      this.tcktMangService.updateTicketStatus(payLoad).subscribe((response) => {
        if(response.success){

          this.alertSer.successAlert("Ticket status update successful")

          this.sendOutputAction('ticketupdated')
        }
      })
    }
  }

  closeTickeView(){
    this.sendOutputAction('ticketviewclose')
  }

  sendOutputAction(reason:string):void {
    const actionInfo = {
      type:'closeTicketView',
      ticketData:reason
    }
    this.onTicketAction.emit(actionInfo);
  }
}

