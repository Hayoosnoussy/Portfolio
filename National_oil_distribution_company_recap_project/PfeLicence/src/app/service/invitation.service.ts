import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormBuilder ,FormGroup,FormControl,ReactiveFormsModule,Validators} from '@angular/forms';

import { Invitation } from '../model/invitation';
@Injectable({
  providedIn: 'root'
})
export class InvitationService {
  
  private baseUrl1 = '/GlobeBusiness/api/invitationdisplay';
  private baseUrl2 = '/GlobeBusiness/api/invitationnb';
  private baseUrl3 = '/GlobeBusiness/api/add-invitations';
  
  private baseUrl5 = '/GlobeBusiness/api/invitation';
  private baseUrl6 = '/GlobeBusiness/api/invitationdelete';
  private baseUrl7 = '/GlobeBusiness/api/file-jobs/start';
  private baseUrl4 = '/GlobeBusiness/api/listdone';
  private baseUrl8 = '/GlobeBusiness/api/invitation/listwait';

  private baseUrl9 = '/GlobeBusiness/api/email-sent';

  





  choixmenu : string = 'A';
  public dataForm :FormGroup;
invitationList: Invitation[];
  





constructor(private http: HttpClient) { }


getData(id: number): Observable<Object> {
  return this.http.get(`${this.baseUrl5}/${id}`);
}

createData(info: Object): Observable<Object> {
  return this.http.post(`${this.baseUrl3}`, info);
}

/*sendData(): Observable<any>{
  return this.http.post(`${this.baseUrl1}`);
}
*/
sendData(info: Object): Observable<Object>{
  return this.http.post(`${this.baseUrl7}`,info);
}


deleteData(id: number): Observable<any> {
 
  return this.http.delete(`${this.baseUrl6}/${id}`, { responseType: 'text' });
}
deleteAllData(id: number): Observable<any> {
 
  return this.http.delete(`${this.baseUrl6}`, { responseType: 'text' });
}
getStat(): Observable<any> {
   
  return this.http.get(`${this.baseUrl2}`);
}
getAll(): Observable<any> {
 
  return this.http.get(`${this.baseUrl1}`);
  }
  getDone(): Observable<any> {
 
    return this.http.get(`${this.baseUrl4}`);
    }
    getWait(): Observable<any> {
 
      return this.http.get(`${this.baseUrl8}`);
      }

      getSent(): Observable<any> {
   
        return this.http.get(`${this.baseUrl9}`);
      }
      
}
