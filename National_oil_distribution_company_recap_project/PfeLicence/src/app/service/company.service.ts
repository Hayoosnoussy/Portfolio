import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormBuilder ,FormGroup,FormControl,ReactiveFormsModule,Validators} from '@angular/forms';
import { Company } from '../model/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private baseUrl = '/GlobeBusiness/api';
  private baseUrl1 = '/GlobeBusiness/api/display';
  private baseUrl2 = '/GlobeBusiness/api/nb';
  private baseUrl3 = '/GlobeBusiness/api/add-company';
  private baseUrl4 = '/GlobeBusiness/api/company';
  private baseUrl5 = '/GlobeBusiness/api/add-company2';
  private baseUrl6 = '/GlobeBusiness/api/companydelete';
  private baseUrl7 = '/GlobeBusiness/api/delete';
  private baseUrl8 = '/GlobeBusiness/api/modify';





  choixmenu : string = 'A';
  public dataForm :FormGroup;
companyList: Company[];
  


islogin : Boolean = false;


constructor(private http: HttpClient) { }
login(login: String, pwd: String): Observable<Object> {
 
  return this.http.get(`${this.baseUrl}/${login}/${pwd}`);
}  

getData(id: number): Observable<Object> {
  return this.http.get(`${this.baseUrl4}/${id}`);
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
