import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { TicketManagementService } from 'src/app/services/ticket-management.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss'],
  encapsulation:ViewEncapsulation.None
})
export class SearchResultComponent implements OnInit {

  @Input()
  data:any [];

  constructor(private tcktMangService:TicketManagementService) {
  }

  ngOnInit(): void {
  }

  onSelectTicket(tckt:any):void {
    this.tcktMangService.searchItemSub.next({
      type:'isViewable',
      ticketData:tckt
    })

  }

}
