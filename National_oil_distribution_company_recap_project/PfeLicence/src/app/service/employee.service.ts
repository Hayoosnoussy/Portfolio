import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormBuilder ,FormGroup,FormControl,ReactiveFormsModule,Validators} from '@angular/forms';

import { Employee } from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = '/GlobeBusiness/api/employee';
  private baseUrl1 = '/GlobeBusiness/api/employeedisplay';
  private baseUrl2 = '/GlobeBusiness/api/employeenb';
  private baseUrl3 = '/GlobeBusiness/api/add-employee';
  
  private baseUrl5 = '/GlobeBusiness/api/add-employee2';
  private baseUrl6 = '/GlobeBusiness/api/employeedelete';
  private baseUrl7 = '/GlobeBusiness/api/employeedelete';
  private baseUrl8 = '/GlobeBusiness/api/employeemodify';





  choixmenu : string = 'A';
  public dataForm :FormGroup;
employeeList: Employee[];
  


islogin : Boolean = false;


constructor(private http: HttpClient) { }
login(login: String, pwd: String): Observable<Object> {
 
  return this.http.get(`${this.baseUrl}/${login}/${pwd}`);
}  

getData(id: number): Observable<Object> {
  return this.http.get(`${this.baseUrl}/${id}`);
}

createData(info: Object): Observable<Object> {
  return this.http.post(`${this.baseUrl5}`, info);
}
updatedata(id: number, value: any): Observable<Object> {
  return this.http.put(`${this.baseUrl8}/${id}`, value);
}


deleteData(id: number): Observable<any> {
 
  return this.http.delete(`${this.baseUrl6}/${id}`, { responseType: 'text' });
}

getAll(): Observable<any> {
 
  return this.http.get(`${this.baseUrl1}`);
  }
  getStat(): Observable<any> {
   
    return this.http.get(`${this.baseUrl2}`);
  }

}
