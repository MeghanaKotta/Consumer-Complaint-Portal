import { AfterViewInit, Component, OnInit, ViewChild, AfterContentInit } from '@angular/core';
import { BreakpointObserver } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';
import { environment } from 'src/environments/environment';
import { TicketManagementService } from 'src/app/services/ticket-management.service';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-manager-layout',
  templateUrl: './manager-layout.component.html',
  styleUrls: ['./manager-layout.component.scss']
})
export class ManagerLayoutComponent implements OnInit{

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  menuItems:any[] = [];
  userdetails:any;
  selectedMenuItem:string = '';
  ticketInfo:any;

  constructor(private observer: BreakpointObserver,
    private tcktMangService:TicketManagementService,
    private _snackBar: MatSnackBar) {
    this.userdetails = this.tcktMangService.appState.userdetails;
    const userRole:string = this.tcktMangService.appState.userRole;
    // console.log('user role    ',userRole);
    const roleAccessLinks:any = environment.roleAccessLinks;
    this.menuItems = roleAccessLinks[userRole].links;
    this.selectedMenuItem = roleAccessLinks[userRole].default_link
    this.tcktMangService.searchItemSub.subscribe((data) => {
      this.onTicketAction(data);
    })

    this.tcktMangService.snackMessageSub.subscribe((msg) => {
      this.showSnackBar(msg);
    })
}

  ngOnInit(): void {
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

  onTicketAction(actionInfo:any){
    
    this.ticketInfo = null;
    const type:string = actionInfo.type;
    let payLoad = {
      userid: this.userdetails.userid,
      ticketid: actionInfo?.ticketData?.ticketid
    }
    if(actionInfo.type == 'isViewable' || actionInfo.type == 'isEditable'){
      this.selectedMenuItem = 'ticket_view';
      this.tcktMangService.getTicketInfo(type,payLoad).subscribe((response) => {
        if(response.success){
          this.ticketInfo = response.data;
        }
      })
    }else if(actionInfo.type == 'closeTicketView'){
      this.selectedMenuItem = 'manage_tickets';
    }else if(actionInfo.type == "showChangePwdView"){
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
