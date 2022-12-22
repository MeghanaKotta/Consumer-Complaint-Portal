import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { CsTicketHistoryComponent } from './cs-ticket-history/cs-ticket-history.component';
import { CustomerLayoutComponent } from './customer-layout/customer-layout.component';



const customerModuleRoutes: Routes = [
    {
        path:'',
        component: CustomerLayoutComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(customerModuleRoutes)
    ],
    exports: [RouterModule]
})
export class CustomerRoutingModule { }
