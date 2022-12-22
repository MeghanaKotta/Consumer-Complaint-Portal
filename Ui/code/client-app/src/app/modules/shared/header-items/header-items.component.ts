import { Component, OnInit } from '@angular/core';
import { TicketManagementService } from 'src/app/services/ticket-management.service';

@Component({
  selector: 'app-header-items',
  templateUrl: './header-items.component.html',
  styleUrls: ['./header-items.component.scss']
})
export class HeaderItemsComponent implements OnInit {

  public avatarPath:string;
  public userdetails:any;
  public showUserMenu:boolean = false;

  constructor(private tcktMangService:TicketManagementService) {
    this.userdetails = this.tcktMangService.appState.userdetails;
    this.avatarPath = "/assets/Images/avatars/"+this.userdetails.avatarid+".png";
  }

  ngOnInit(): void {
    this.tcktMangService.userDetailsUpdatedSub.subscribe((data) =>{
      this.userdetails = this.tcktMangService.appState.userdetails;
      this.avatarPath = "/assets/Images/avatars/"+this.userdetails.avatarid+".png";
  
    })
  }

  doLogout() {
    this.tcktMangService.doLogout();
  }

  toggleUserMenu():void {
    this.showUserMenu = !this.showUserMenu;
  }

  showChangePwdView():void {
    this.showUserMenu = false;
    this.tcktMangService.searchItemSub.next({
      type:'showChangePwdView'
    })
  }

  showUpdateAvatar():void {
    this.showUserMenu = false;
    this.tcktMangService.searchItemSub.next({
      type:'showUpdateAvatarView'
    })
  }

}
