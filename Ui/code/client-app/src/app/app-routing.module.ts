import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';

import { LoginComponent } from './components/login/login.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { RegistrationComponent } from './components/registration/registration.component';

import { CustomerGuard } from './modules/customer/gaurds/customer.gaurd';
import { ManagerGaurd } from './modules/manager/gaurd/manager.gaurd';

const appRoutes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegistrationComponent
    },
    {
        path: 'customer',
        loadChildren: () => import('./modules/customer/customer.module').then(m => m.CustomerModule),
        canLoad:[CustomerGuard]
    },
    {
        path: 'manager',
        loadChildren: () => import('./modules/manager/manager.module').then(m => m.ManagerModule),
        canLoad:[ManagerGaurd]
    },
    {
        path: 'orgmember',
        loadChildren: () => import('./modules/manager/manager.module').then(m => m.ManagerModule),
        canLoad:[ManagerGaurd]
    },
    {
        path:'',
        component:LayoutComponent
    },
    {
        path:'**',
        component:PageNotFoundComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule { }
