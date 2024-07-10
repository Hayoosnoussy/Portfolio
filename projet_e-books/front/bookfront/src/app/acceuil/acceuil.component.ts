import { Component, OnInit, Inject } from '@angular/core';
import {FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from "rxjs";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Produit } from '../model/produit';
import { ProduitService } from '../service/produit.service';
import { AddproduitComponent } from '../produit/addproduit/addproduit.component';
@Component({
  selector: 'app-acceuil',
  templateUrl: './acceuil.component.html',
  styleUrls: ['./acceuil.component.scss']
})
export class AcceuilComponent implements OnInit {
  
  produit : Produit;
  constructor(public crudApi: ProduitService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddproduitComponent>,) { }
 
  ngOnInit() {

  }

  deleteItem(item:Produit) {
    this.crudApi.q = this.crudApi.q - 1 ;
    this.crudApi.Total= this.crudApi.Total - item.prix;
    this.crudApi.Totalsub= this.crudApi.Totalsub -( item.prix) ;
    const index: number = this.crudApi.produits.indexOf(item);
    if (index !== -1) {
      this.crudApi.produits.splice(index, 1);
    }        

  }

  deleteAlll(){
    this.crudApi.q = 0;
    this.crudApi.Total = 0;
    this.crudApi.Totalsub = 0;
    this.crudApi.produits.splice(0,this.crudApi.produits.length);

  }

} 


