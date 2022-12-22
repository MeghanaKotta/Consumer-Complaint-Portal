import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketHistoryComponent } from './ticket-history/ticket-history.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { GlobalSearchComponent } from './global-search/global-search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { HeaderItemsComponent } from './header-items/header-items.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { UpdateAvatarComponent } from './update-avatar/update-avatar.component';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    TicketHistoryComponent,
    SearchResultComponent,
    GlobalSearchComponent,
    ChangePasswordComponent,
    ResetPasswordComponent,
    HeaderItemsComponent,
    UpdateAvatarComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatIconModule,
    MatCardModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule
  ],
  exports:[
    TicketHistoryComponent,
    GlobalSearchComponent,
    HeaderItemsComponent,
    ChangePasswordComponent,
    UpdateAvatarComponent
  ]
})
export class SharedModule { }
