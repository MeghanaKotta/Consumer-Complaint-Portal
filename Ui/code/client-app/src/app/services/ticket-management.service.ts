import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { environment } from '../../environments/environment'
import { AlertService } from './alert.service';

export enum ErrorMessage {
  Login_Error = "Login_Error",
  Registration_Error = "Reg_Error"
}

@Injectable({
  providedIn: 'root'
})
export class TicketManagementService {
  baseUrl = environment.basePath;

  private _state;
  private INITIAL_STATE = {
    userdetails:{
    },
    associatedDepOrgs:{
    },
    userRole:'',
    roles: [],
    depOrgMapping:{},
    sessionkey:null,
    tagsList:[]
  }

  public errorMsgSub:Subject<any> = new Subject();
  public searchItemSub:Subject<any> = new Subject();
  public userDetailsUpdatedSub:Subject<any> = new Subject();
  public snackMessageSub:Subject<any> = new Subject();

  constructor(private _http: HttpClient, private router:Router, private activatedRoute:ActivatedRoute,private alertSer:AlertService) { 
    this._state = {...this.INITIAL_STATE};
    this.getRoleInfo();
    this.getDepOrgMapping();
    this.getTagslist();
  }

  userLogin(data:any){
    if(data){
      const loginUrl = this.baseUrl + environment.api_routes.login;
      this._http.post(loginUrl, data).subscribe((response: any) => {
          try {
            if (response.success) {
              this._state = {
                ...this._state,
                userdetails:{...response.data.userdetails},
                sessionkey:response.data.sessionkey,
                associatedDepOrgs:{...response.data.associatedDepOrgs}
              }
              sessionStorage.setItem('UserDetails',JSON.stringify({
                userdetails:this._state.userdetails,
                sessionkey:this._state.sessionkey
              }));
              this.moveToUserModule();
            }else{
              this.errorMsgSub.next({
                errorType:ErrorMessage.Login_Error,
                msg:response.reason
              })
              console.log(response.reason);
            }
          } catch (error) {
            //on Login fail
          }
        }, (err) => {
          //api error
        });;
    }
  }

  doLogout():void {
    const payLoad = {
      sessionkey:this._state.sessionkey
    }
    const registrationUrl = this.baseUrl + environment.api_routes.logout;
    this._http.post(registrationUrl, payLoad).subscribe((response: any) => {
      if(response.success){
        this._state = {
          ...this._state,
          userdetails:{},
          userRole:'',
          sessionkey:''
        }
        sessionStorage.setItem('UserDetails',JSON.stringify(this._state));
        this.router.navigate(['/login'],{});
      }
    });
  }

  userRegister(data:any){
    if(data){
      let isOrgRegistration:boolean
      const registrationUrl = this.baseUrl + environment.api_routes.register;
      if(data.isOrgMember){
        isOrgRegistration = true;
      }
      this._http.post(registrationUrl, data).subscribe((response: any) => {
          try {
            if (response.success) {
<<<<<<< HEAD
              this.alertSer.successAlert("Registration sucessfull, Please login with your credentials")
=======
              this.snackMessageSub.next('Registration sucessfull, Please login with your credentials')
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
              if(!isOrgRegistration){
                this.router.navigate(['/login'],{});
              }
              //{"data":{"success":true},"success":true,"reason":null}
            }else{
              this.errorMsgSub.next({
                errorType:ErrorMessage.Registration_Error,
                msg:response.reason
              })
                console.log(response.reason);
            }
          } catch (error) {
            //on Login fail
          }
        }, (err) => {
          //api error
        });;
    }
  }

  getRoleInfo(): void {
    // http://localhost:8080/ticketHandling/data/getRoleInfo
    const roleInfoUrl = this.baseUrl + environment.api_routes.getRoleInfo;
    this._http.post(roleInfoUrl,{}).subscribe((response: any) => {
      try {
        if (response) {
          this._state = {
            ...this._state,
            roles:([...response.data] as any)
          }
          //{"data":[{"id":1,"rolename":"Manager"},{"id":2,"rolename":"OrgMember"},{"id":3,"rolename":"Customer"}],"success":true,"reason":null}
        }
      } catch (error) {
        //on Login fail
      }
    }, (err) => {
      //api error
    });
  }

  getDepOrgMapping():void {
    //http://localhost:8080/ticketHandling/data/getDepOrgMapping
    const depOrgMappingUrl = this.baseUrl + environment.api_routes.getDepOrgMapping;
    this._http.post(depOrgMappingUrl,{}).subscribe((response: any) => {
      try {
        if (response.success) {
          this._state = {
            ...this._state,
            depOrgMapping:{...response.data}
          }
          //{"data":[{"id":1,"rolename":"Manager"},{"id":2,"rolename":"OrgMember"},{"id":3,"rolename":"Customer"}],"success":true,"reason":null}
        }else{
          console.log('unable to fetch dep and org details');
        }
      } catch (error) {
        //on Login fail
      }
    }, (err) => {
      //api error
    });
  }

  getTagslist():void {
    const tagsListUrl = this.baseUrl + environment.api_routes.getTagsList;
    this._http.get(tagsListUrl).subscribe((response: any) => {
      try {
        if (response.success) {
          this._state = {
            ...this._state,
            tagsList:([...response.data] as any)
          }
          //{"data":[{"id":1,"name":"Wrong Product"},{"id":2,"name":"Purchase block"},{"id":3,"name":"Refund or return1"},{"id":4,"name":"Refund or return2"},{"id":5,"name":"Purchase block1"},{"id":6,"name":"Purchase block"},{"id":7,"name":"Wrong Product"}],"success":true,"reason":null}
        }else{
          console.log('unable to fetch dep and org details');
        }
      } catch (error) {
        //on Login fail
      }
    }, (err) => {
      //api error
    });
  }

  createTicket(payLoad:any):Observable<any> {
    const createTicketUrl = this.baseUrl + environment.api_routes.createTicket;
    return this._http.post(createTicketUrl,payLoad);
  }

  getManagerEligibleJoiningDepOrg():Observable<any>{
    const uid = this._state.userdetails.userid;
    const getManagerEligibleJoiningDepOrgUrl = this.baseUrl + environment.api_routes.getManagerEligibleJoiningDepOrg+"?uid="+uid;
    return this._http.get(getManagerEligibleJoiningDepOrgUrl);
  }

  joinManagerInDepOrg(payLoad):Observable<any> {
    const joinManagerInDepOrgUrl = this.baseUrl + environment.api_routes.joinManagerInDepOrg;
    return this._http.post(joinManagerInDepOrgUrl,payLoad);
  }

  getDepOrgMappingOnUserId():Observable<any> {
    const uid = this._state.userdetails.userid;
    const getDepOrgMappingOnUserIdUrl = this.baseUrl + environment.api_routes.getDepOrgMappingOnUserId+"?uid="+uid;
    return this._http.get(getDepOrgMappingOnUserIdUrl);
  }

  getPieChartInfo(query:string){
    // http://localhost:8080/ticketHandling/ticket/getTheTicketsPieChartData?orgid=2&depid=4
    const ticketsPieChartUrl = this.baseUrl + environment.api_routes.getTheTicketsPieChartData +'?'+ query;
    return this._http.get(ticketsPieChartUrl)
  }

  getTicketsDisplayInfo(payLoad):Observable<any> {
    const getTicketsDisplayInfoUrl = this.baseUrl + environment.api_routes.getTicketsDisplayInfo;
    return this._http.post(getTicketsDisplayInfoUrl,payLoad);
  }

  getTicketInfo(type:string, payLoad:any):Observable<any> {
    const apiPath = type == 'isViewable' ? environment.api_routes.getTicketViewInfo : environment.api_routes.getTicketEditInfo; 
    const getTicketViewInfoUrl = this.baseUrl + apiPath;
    return this._http.post(getTicketViewInfoUrl,payLoad);
  }

  bookMarkTicket(payLoad:any):Observable<any> {
    const getTicketViewInfoUrl = this.baseUrl + environment.api_routes.bookMarkTicket;
    return this._http.post(getTicketViewInfoUrl,payLoad);
  }

  checkSession(sid:string):Observable<any> {
    //http://localhost:8080/ticketHandling/user/checksession
    const payLoad = {
      sessionkey:sid
    }
    const checkSessionUrl = this.baseUrl + environment.api_routes.checkSession;
    return this._http.post(checkSessionUrl,payLoad);
  }

  get appState ():any {
    return this._state;
  }

  checkInitialRouteChange():void {
    const userDetails = sessionStorage.getItem('UserDetails');
    if(userDetails){
      const sessionkey = JSON.parse(userDetails).sessionkey;
      if(sessionkey){
        this.checkSession(sessionkey).subscribe((response: any) => {
          try {
            if (response.success) {
              this._state = {
                ...this._state,
                userdetails:{...response.data.userdetails},
                sessionkey:sessionkey,
                associatedDepOrgs:{...response.data.associatedDepOrgs}
              }
              this.moveToUserModule();
              //{"data":{"valid":true},"success":true,"reason":null}
            }else{
              this.router.navigate(['/login'],{});
            }
          } catch (error) {
            //on Login fail
          }
        }, (err) => {
          //api error
        });
      }else{
        this.router.navigate(['/login'],{});
      }
    }else{
      this.router.navigate(['/login'],{});
    }
  }

  updateAvatar(avatarid:number):Observable<any> {
    this._state.userdetails.avatarid = avatarid;
    const { userid } = this._state.userdetails;
    const payLoad = {
      userid:userid,
      avatarid:avatarid
    }
    const updateAvatarUrl = this.baseUrl + environment.api_routes.updateAvatar;
    return this._http.post(updateAvatarUrl,payLoad);
  }

  getEligibleUserIdsListForTicketEdit(ticketid:number):Observable<any> {
    const { userid } = this._state.userdetails;
    const payLoad = {
      userid:userid,
      ticketid:ticketid
    }

    const checkSessionUrl = this.baseUrl + environment.api_routes.getEligibleUserIdsListForTicketEdit;
    return this._http.post(checkSessionUrl,payLoad);

  }

  updateTicketStatus(payLoad:any):Observable<any> {
    const checkSessionUrl = this.baseUrl + environment.api_routes.moveTicketOpenToProgress;
    return this._http.post(checkSessionUrl,payLoad);
  }

  moveToUserModule(){
    const roleid = this._state.userdetails.roleid;
    const matchedRole:any = this._state.roles.find((role:any) => role.id == roleid);
    if(matchedRole){
      const rolename = matchedRole.rolename.toLowerCase();
      this._state = {
        ...this._state,
        userRole:rolename
      }
      this.router.navigate(['/'+rolename.toLowerCase()],{});
      // this.router.navigate(['/manager'],{});
    }
  }

  getUserTickets():Observable<any>{
    const routePath = this.isCustomer() ? 
    environment.api_routes.getMyCustomerTickets : environment.api_routes.getMyTickets;
    const payLoad = {
      userid:this._state.userdetails.userid
    }
    const getUserTicketsUrl = this.baseUrl + routePath;
    return this._http.post(getUserTicketsUrl,payLoad);
  }

  searchTickets(searchStr:string):Observable<any> {
    const routePath = this.isCustomer() ? 
    environment.api_routes.searchCustomerTickets : environment.api_routes.searchTickets;
    const payLoad = {
      filterCondition:searchStr,
      userid:this._state.userdetails.userid
    }
    const searchTicketsUrl = this.baseUrl + routePath;
    return this._http.post(searchTicketsUrl,payLoad);
  }

  changepwd(password:string):Observable<any> {
    const payLoad = {
      userid:this._state.userdetails.userid,
      password:password
    }
    const updatePwdUrl = this.baseUrl + environment.api_routes.updatePassword;
    return this._http.post(updatePwdUrl,payLoad);
  }

  doClientLogout():void {
    this._state = this.INITIAL_STATE;
    // sessionStorage.setItem('UserDetails',JSON.stringify({
    //   userdetails:this._state.userdetails,
    //   sessionkey:this._state.sessionkey
    // }));
    sessionStorage.removeItem('UserDetails')
    this.router.navigate(['/login'],{});
  }

  isCustomer ():boolean {
    return this._state && this._state.userdetails && this._state.userdetails.roleid == 3;
  }

  isOrgmember ():boolean {
    return this._state && this._state.userdetails && this._state.userdetails.roleid == 2;
  }

  isManager ():boolean {
    return this._state && this._state.userdetails && this._state.userdetails.roleid == 1;
  }
}
