import { Component } from '@angular/core';
import { AlertService } from './services/alert.service';
import { TicketManagementService } from './services/ticket-management.service';
<<<<<<< HEAD
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

=======
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'client-app';
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  constructor(private ticketMangementService:TicketManagementService,private _snackBar: MatSnackBar,private alertSer:AlertService) {
    this.ticketMangementService.checkInitialRouteChange();
    this.alertSer.alertSubject.subscribe((data:any)=>{
      this.openSnackBar(data,'Done')
    })
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action,{
      duration: 5 * 1000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition
    });
  }

}
