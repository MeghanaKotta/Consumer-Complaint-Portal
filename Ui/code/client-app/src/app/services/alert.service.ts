import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AlertService {
    
    content: any;  
    public alertSubject = new Subject();
      
    constructor(private http: HttpClient) { }
    successAlert(Msg) {
        this.alertSubject.next(Msg);
    }

}
