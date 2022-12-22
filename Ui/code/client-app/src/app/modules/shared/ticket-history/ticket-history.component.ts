import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TicketManagementService } from 'src/app/services/ticket-management.service';

@Component({
  selector: 'app-ticket-history',
  templateUrl: './ticket-history.component.html',
  styleUrls: ['./ticket-history.component.scss']
})
export class TicketHistoryComponent implements OnInit {

  @Output()
  onTicketClick:EventEmitter<any> = new EventEmitter<any>();

  public ticketsData:any [] = [];
  public openTickets:any [] = [];
  public progressTickets:any [] = [];
  public closedTickets:any [] = [];
  public isCustomer:boolean = false;
  public avatarBasePath:string = '';

  constructor(private tcktMangService:TicketManagementService) {
    this.avatarBasePath = "/assets/Images/avatars/";
    this.tcktMangService.getUserTickets().subscribe((response) => {
      if(response.success){
        this.isCustomer = this.tcktMangService.isCustomer();
        this.ticketsData = response.data;
        if(this.ticketsData.length > 0){
          this.openTickets = this.ticketsData.filter((ticket) => ticket.status == 0);
          this.progressTickets = this.ticketsData.filter((ticket) => ticket.status == 1);
          this.closedTickets = this.ticketsData.filter((ticket) => ticket.status == 2);
        }
      }
    })
  }

  ngOnInit(): void {
  }

  getAvatarPath(avatarid:number):string {
    return this.avatarBasePath+avatarid+".png"
  }

  updateAction(type:string,tckt:any):void {
    // if((!tckt.isViewable && type == 'isViewable') ||
    // (!tckt.isEditable && type == 'isEditable')){
    //   return;
    // }
    const actionInfo = {
      type:type,
      ticketData:tckt
    }
    this.onTicketClick.emit(actionInfo);
  }

  breakTags(tagsI)
  {
    const tagIds = tagsI.slice(1,tagsI.length-1).split(";");
    return tagIds.map((id) => "/assets/Images/tags/"+id+".png");
  }


}
