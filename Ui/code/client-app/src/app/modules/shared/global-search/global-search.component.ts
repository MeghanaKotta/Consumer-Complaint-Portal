import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {fromEvent } from 'rxjs';
import { filter, debounceTime, distinctUntilChanged, tap } from 'rxjs/operators';
import { TicketManagementService } from 'src/app/services/ticket-management.service';


@Component({
  selector: 'app-global-search',
  templateUrl: './global-search.component.html',
  styleUrls: ['./global-search.component.scss']
})
export class GlobalSearchComponent implements OnInit, AfterViewInit {

  public searchQuery:string = ''
  constructor(private tcktMangService:TicketManagementService) {
    this.tcktMangService.searchItemSub.subscribe((data) => {
      this.searchQuery = ''
    })
  }

  ngOnInit(): void {
  }

  @ViewChild('input', {static: true}) input: ElementRef;
  public searchResult:any [] = [];

  ngAfterViewInit() {
            // server-side search
        fromEvent(this.input.nativeElement,'keyup')
            .pipe(
                filter(Boolean),
                debounceTime(1500),
                distinctUntilChanged(),
                tap((event:KeyboardEvent) => {
                  this.searchQuery = this.input.nativeElement.value;
                  if(this.searchQuery != ''){
                    this.tcktMangService.searchTickets(this.searchQuery).subscribe((response) => {
                      if(response.success){
                        this.searchResult = response.data;
                      }
                    })
                  }
                })
            )
            .subscribe();
  }


}
