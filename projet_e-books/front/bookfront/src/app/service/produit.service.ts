import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormBuilder ,FormGroup,FormControl,ReactiveFormsModule,Validators} from '@angular/forms';
import { Produit } from '../model/produit';
@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  private baseUrl = '/api/produits';
  private baseUrl1 = '/api/produits/3';
  private baseUrl2 = '/api/produits/5';
  private baseUrl3 = '/api/produits/8';
  private baseUrl5 = '/api/produits/10';
  private baseUrl6 = '/api/produits/11';
  private baseUrl7 = '/api/produits/12';
  private baseUrl8 = '/api/produits/13';
  private baseUrl9 = '/api/produits/14';

  n:number=0;

q:number=0;
ps:string = 'A';
  choixmenu : string = 'A';
  host :string = "http://localhost:8080";
  Total:number=0;
  Totalsub:number=0;

  public dataForm :FormGroup;
  produitList: Produit[];
  produits: Produit[]=new Array();

  constructor(private http: HttpClient) { }
 
 
  search(cherche: String): Observable<any> {
   
    return this.http.get(`${this.baseUrl1}/${cherche}`);
  }  
  
  categ(categorie: String): Observable<any> {
   
    return this.http.get(`${this.baseUrl2}/${categorie}`);
  }  

  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }  
  getStat(): Observable<any>{
   
    return this.http.get(`${this.baseUrl5}`);
  }
  getState(): Observable<any>{
   
    return this.http.get(`${this.baseUrl6}`);
  }
  getStatr(): Observable<any>{
   
    return this.http.get(`${this.baseUrl7}`);
  }
  getStatt(): Observable<any>{
   
    return this.http.get(`${this.baseUrl8}`);
  }
  getStaty(): Observable<any>{
   
    return this.http.get(`${this.baseUrl9}`);
  }
  createData(formData: FormData): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('content-type','application/json');
    return this.http.post(`${this.baseUrl}`, formData,{headers});
  }
  updatedata(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }
  updatedataaaa(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl3}/${id}`, value);
  }

  deleteData(id: number): Observable<any> {
   
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getAll(): Observable<any> {
   
    return this.http.get(`${this.baseUrl}`);
  }
   
 
}
 