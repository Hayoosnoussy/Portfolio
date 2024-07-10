import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormBuilder ,FormGroup,FormControl,ReactiveFormsModule,Validators} from '@angular/forms';
import { User } from '../model/user';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = '/api/users';
  private baseUrl1 = '/api/users/6';
  private resetPassword ='/api/users/resetPassword'


  choixmenu : string = 'A';
  public dataForm :FormGroup;
  userList: User[];
  islogin = false;
  admin = false;
 

  constructor(private http: HttpClient) { }
  login(login: String, pwd: String): Observable<Object> {
   
    return this.http.get(`${this.baseUrl}/${login}/${pwd}`);
  }  

  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
 
  createData(info: Object): Observable<Object> {
    const headers = new HttpHeaders();
    headers.append('content-type','application/json');
    return this.http.post(`${this.baseUrl}`, info,{headers});
  }
  updatedata(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }
  

  deleteData(id: number): Observable<any> {
   
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getAll(): Observable<any> {
   
    return this.http.get(`${this.baseUrl}`);
  }
   
 
}
