import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AddOrgMemberComponent } from './add-org-member/add-org-member.component';
import { JoinOrganizationComponent } from './join-organization/join-organization.component';
import { ManagerLayoutComponent } from './manager-layout/manager-layout.component';


const managerModuleRoutes: Routes = [
    {
        path:'',
        component: ManagerLayoutComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(managerModuleRoutes)
    ],
    exports: [RouterModule]
})
export class ManagerRoutingModule { }
