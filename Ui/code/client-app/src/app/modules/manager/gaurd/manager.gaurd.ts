import { Injectable } from '@angular/core';
import {
    CanActivate,
    Router,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    CanActivateChild,
    NavigationExtras,
    CanLoad,
    Route
} from '@angular/router';
import { TicketManagementService } from 'src/app/services/ticket-management.service';

@Injectable({
    providedIn: 'root',
})
export class ManagerGaurd implements CanActivate, CanActivateChild, CanLoad {
    constructor(private ticketMangementService:TicketManagementService, private router: Router) {}

    public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let url: string = state.url;
        return this.checkLogin(url);
    }

    public canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.canActivate(route, state);
    }

    public canLoad(route: Route): boolean {
        let url = `/${route.path}`;
        return this.checkLogin(url);
    }

    public checkLogin(url: string): boolean {
        return true;
        //this.ticketMangementService
        //if not store the current url post criteria match will redirect
        let navigationExtras: NavigationExtras = {
            queryParams: { 'session_id': 'sessionId' },
            fragment: 'anchor'
        };
        this.router.navigate(['/login'], navigationExtras);
        return false;
    }
}
