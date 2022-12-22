import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JoinOrganizationComponent } from './join-organization/join-organization.component';
import { AddOrgMemberComponent } from './add-org-member/add-org-member.component';
import { ManagerRoutingModule } from './manager-routing.module';
import { ManagerLayoutComponent } from './manager-layout/manager-layout.component';
import {MatSelectModule} from '@angular/material/select';
import {MatCardModule} from '@angular/material/card';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { ChartModule } from '../chart/chart.module';
import { ManageTicketComponent } from './manage-ticket/manage-ticket.component';
import { TicketComponent } from './ticket/ticket.component';
import { MatRadioModule } from '@angular/material/radio';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    JoinOrganizationComponent,
    AddOrgMemberComponent,
    ManagerLayoutComponent,
    ManageTicketComponent,
    TicketComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    MatSelectModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatSidenavModule,
    MatToolbarModule,
    ManagerRoutingModule,
    MatRadioModule,
    ChartModule,
    SharedModule
  ]
})
export class ManagerModule { }
