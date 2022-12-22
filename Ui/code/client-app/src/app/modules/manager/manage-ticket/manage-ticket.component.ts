import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { TicketManagementService } from 'src/app/services/ticket-management.service';
import {fromEvent, Subscription } from 'rxjs';
import { filter, debounceTime, distinctUntilChanged, tap } from 'rxjs/operators';

@Component({
  selector: 'app-manage-ticket',
  templateUrl: './manage-ticket.component.html',
  styleUrls: ['./manage-ticket.component.scss'],
  encapsulation:ViewEncapsulation.None
})
export class ManageTicketComponent implements OnInit {

  @ViewChild('searchInput', {static: false}) searchInput: ElementRef;

  @Output()
  onTicketClick:EventEmitter<any> = new EventEmitter<any>();

  totalTicketsCount:number;
  values;
  searchQuery:string;
  isSearchForBookmark:boolean = false;
  labels=['Open Tickets', 'Inprogress Tickets', 'Closed Tickets'];//0,1,2
  colors = ['#34a853', '#FBBC05', '#EA4335'];
  ticketsDetails: any[];
  totalPages:number;

  departments: any[] = [];

  organizations: any [] = [];
  public selectedDept:number;
  public selectedDeptName:string = '';
  public selectedDeptDesc:string = '';
  public selectedOrg:number;
  public selectedOrgName:string = '';
  public selectedOrgDesc:string = '';
  public depOrgMapping:any;
  public pieChartDetailsRequested:boolean = false;
  public selectedTicketStatus:number;
  public isOrgMem:boolean = false;
  private inputSub:Subscription;
  
  constructor(private tcktMangService:TicketManagementService) {
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
          if(this.tcktMangService.isOrgmember()){
            this.isOrgMem = true;
            this.selectedDept = Number(Object.keys(this.tcktMangService.appState.associatedDepOrgs)[0]);
            this.selectedDeptName = this.tcktMangService.appState.associatedDepOrgs[this.selectedDept].depname;
            this.selectedDeptDesc = this.tcktMangService.appState.associatedDepOrgs[this.selectedDept].depdesc;
            this.onDeptChange(null);
            this.selectedOrg = Number(this.tcktMangService.appState.associatedDepOrgs[this.selectedDept].orgsList[0].id);
            this.selectedOrgName = this.tcktMangService.appState.associatedDepOrgs[this.selectedDept].orgsList[0].orgname;
            this.selectedOrgDesc = this.tcktMangService.appState.associatedDepOrgs[this.selectedDept].orgsList[0].orgdesc;
            this.onOrgChange(null);
          }else{
            this.onDeptChange(null);
          }
        }
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
      this.selectedOrg = undefined;
    }
    this.ticketsDetails = [];
  }

  onOrgChange($evt:any):void {
    this.ticketsDetails = [];
    const queryString:string = 'orgid='+this.selectedOrg+'&depid='+this.selectedDept;
    this.tcktMangService.getPieChartInfo(queryString).subscribe((response: any) => {
      this.pieChartDetailsRequested = true;
      try {
        if (response.success) {
          if(response.data){
            this.selectedTicketStatus = undefined;
            if(response.data.length > 0){
              this.values = [response.data[0].openticketcount,response.data[0].progressticketcount,response.data[0].closedtcketcount];
              this.totalTicketsCount = response.data[0].totalticketscount;
              if(this.totalTicketsCount > 0 && !this.inputSub){
                this.addInputChange();
              }
            }else {
              this.values = [];
              this.totalTicketsCount = 0;
            }
          }else {
            if(this.inputSub){
              this.inputSub.unsubscribe();
              this.inputSub = null;
            }
          }
        }else{
          console.log('unable to fetch dep and org details');
        }
      } catch (error) {
        //on Login fail
      }
    }, (err) => {
      //api error
    });
  }

  onClick(idx: number) {
    this.selectedTicketStatus = idx;
    this.fetchTicketDetails();
  }

  fetchTicketDetails():void {
    const userid = this.tcktMangService.appState.userdetails.userid;
    const payLoad = {
      "userid":userid,
      "ticketstatus":this.selectedTicketStatus,
      "pageNumber":1,
      "numberOfElementsRequired":100,
      "contentFilter" : this.searchQuery ? this.searchQuery : '',
      "isBookMarkedStatus": this.isSearchForBookmark ? 1 : 0,
      "depId":this.selectedDept,
      "orgId":this.selectedOrg
    }
    this.tcktMangService.getTicketsDisplayInfo(payLoad).subscribe((response:any) => {
      if(response.success){
        this.ticketsDetails = response.data.data; 
        this.totalPages = response.data.totalPages; 
        if(this.ticketsDetails.length > 0 && !this.inputSub){
          this.addInputChange();
        }
      }else {
        if(this.inputSub){
          this.inputSub.unsubscribe();
          this.inputSub = null;
        }
      }

    })
  }

  addInputChange() {
    this.inputSub = fromEvent(this.searchInput.nativeElement,'keyup')
      .pipe(
          filter(Boolean),
          debounceTime(1500),
          distinctUntilChanged(),
          tap((event:KeyboardEvent) => {
            this.searchQuery = this.searchInput.nativeElement.value;
            this.fetchTicketDetails();
          })
      )
      .subscribe();
  }

  onBookmarkStateChange():void {
    this.isSearchForBookmark = !this.isSearchForBookmark;
    this.fetchTicketDetails();
  }

  updateAction(type:string,tckt:any):void {
    if((!tckt.isViewable && type == 'isViewable') ||
    (!tckt.isEditable && type == 'isEditable')){
      return;
    }
    const actionInfo = {
      type:type,
      ticketData:tckt
    }
    this.onTicketClick.emit(actionInfo);
  }

  update() {
    this.values=[10, 30, 20, 40];
  }

  ngOnInit(): void {
  }

  updateBookMarkStatus(ticket):void {
    const { userid } = this.tcktMangService.appState.userdetails;
    const payLoad = {
      userid: userid,
      ticketid: ticket.ticketid,
      status:!ticket.isBookMarked ? 1 : 0
    }
    this.tcktMangService.bookMarkTicket(payLoad).subscribe((response) => {
      if(response.success) {
        if(response.data.updated == true){
          this.fetchTicketDetails();
        }
      }
    })//

  }

}
