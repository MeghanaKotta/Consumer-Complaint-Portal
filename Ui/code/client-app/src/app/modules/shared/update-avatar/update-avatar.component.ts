import { Component, OnInit } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';
import { TicketManagementService } from 'src/app/services/ticket-management.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-update-avatar',
  templateUrl: './update-avatar.component.html',
  styleUrls: ['./update-avatar.component.scss']
})
export class UpdateAvatarComponent implements OnInit {

  public avatarsPath:string [] = [];
  public currentAvatarid:number;
  public isChanged:boolean = false;
  public selectedAvatarid:number;

  constructor(private tcktMangService:TicketManagementService,private alertSer:AlertService) {
    this.currentAvatarid = this.tcktMangService.appState.userdetails.avatarid;
    this.selectedAvatarid = this.currentAvatarid;
    this.avatarsPath = Array(environment.avatarsCount).fill(0).map((value,i) => "/assets/Images/avatars/"+(i+1)+".png")
  }

  ngOnInit(): void {
  }

  onSelect(selectedId:number):void {
    if(this.currentAvatarid != selectedId){
      this.selectedAvatarid = selectedId;
      this.isChanged = true;
    }else {
      this.isChanged = false;
    }
  }

  updateAvatar():void {
    this.tcktMangService.updateAvatar(this.selectedAvatarid).subscribe((response) => {
      if(response.success){
        // alert('avatar updated success');
<<<<<<< HEAD
        this.alertSer.successAlert("Updated successfully !!")
=======
        this.tcktMangService.snackMessageSub.next('Avatar updated successfully');
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        this.tcktMangService.userDetailsUpdatedSub.next(true);
      }
    })
  }

}
