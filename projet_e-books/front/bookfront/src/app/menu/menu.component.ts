import { Component, OnInit, Inject } from '@angular/core';
import {FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from "rxjs";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AddproduitComponent } from '../produit/addproduit/addproduit.component';
import { Produit } from '../model/produit';
import { ProduitService } from '../service/produit.service';
import { UserService } from '../service/user.service';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
nom:string;
ps:string = 'A';
azerty:string = 'A';
proList :Produit[];
n:number;
alim:number;
images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);

  produit : Produit;
  prodList :Produit[];
  name : String;
  email : String;
  role : String;

  constructor(public crudApi: ProduitService,public slm :UserService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddproduitComponent>,) { }
 
  ngOnInit() {
    this.getData();
    this.ps= 'A';
    this.name = localStorage.getItem('name');
    this.email = localStorage.getItem('email');
    this.role = localStorage.getItem('role');

  }
  
 
  
  zelzel(){
    localStorage.clear();
    this.slm.islogin = false;
  }
  addproduit()
  {
 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.height="90%";
    //dialogConfig.data="gdddd";
    this.matDialog.open(AddproduitComponent, dialogConfig);
  }
  closepub(){
    this.azerty='R';
  }

  getData() {
   
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.produitList = response;}
     );
  }
  
  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this Client ?')) {
    this.crudApi.deleteData(id)
      .subscribe(
        data => {
          console.log(data);
          this.toastr.warning(' data successfully deleted!'); 
          this.getData();
        },
        error => console.log(error));
  }
  }
  selectData(item : Produit) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.height="90%";
    
    this.matDialog.open(AddproduitComponent, dialogConfig);
     
      
  }
hello(d:string){
  this.ps= 'C';
  this.crudApi.categ(d).subscribe(response =>{this.proList = response;}
    );

}
  recherche(){
    this.ps= 'B';
       this.crudApi.search(this.nom).subscribe(response =>{this.prodList = response;}
      );
  }
addat(item :Produit){
 this.crudApi.q = this.crudApi.q + 1 ;
this.crudApi.produits.push(item);
(this.crudApi.Total= this.crudApi.Total + item.prix);
this.crudApi.Totalsub= this.crudApi.Total + 7;
}
coucou(){
  this.crudApi.n =0;
}

}
