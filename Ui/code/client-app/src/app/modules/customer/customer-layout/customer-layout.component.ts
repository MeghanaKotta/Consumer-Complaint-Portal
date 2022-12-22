import { Component, OnInit, ViewChild } from '@angular/core';
import { BreakpointObserver } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';
import { TicketManagementService } from 'src/app/services/ticket-management.service';
import { environment } from '../../../../environments/environment'
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-customer-layout',
  templateUrl: './customer-layout.component.html',
  styleUrls: ['./customer-layout.component.scss']
})
export class CustomerLayoutComponent implements OnInit {

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  menuItems:any[] = []
  selectedMenuItem:string = '';

  constructor(private observer: BreakpointObserver,
    private tcktMangService:TicketManagementService,
    private _snackBar: MatSnackBar) {
      this.menuItems = environment.roleAccessLinks.customer.links;
      this.selectedMenuItem = environment.roleAccessLinks.customer.default_link
  }

  ngOnInit(): void {
    this.tcktMangService.searchItemSub.subscribe((data) => {
      this.onHeaderItemSelect(data);
    })

    this.tcktMangService.snackMessageSub.subscribe((msg) => {
      this.showSnackBar(msg);
    })
  }

  ngAfterViewInit() {
    this.observer.observe(['(max-width: 800px)']).subscribe((res) => {
      if (res.matches) {
        this.sidenav.mode = 'over';
        this.sidenav.close();
      } else {
        this.sidenav.mode = 'side';
        this.sidenav.open();
      }
    });
  }

  onMenuItemClick(str:string):void {
    this.selectedMenuItem = str;
  }

  onHeaderItemSelect(actionInfo:any){
    if(actionInfo.type == "showChangePwdView"){
      this.selectedMenuItem = 'change_pwd';
    }else if(actionInfo.type == "showUpdateAvatarView"){
      this.selectedMenuItem = 'update_avatar';
    }
  }
  showSnackBar(message:string){
    this._snackBar.open(message, '' , {
      duration: 3 * 1000,
    });
  }

}
