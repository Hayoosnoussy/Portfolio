import { Component, OnInit, Inject } from '@angular/core';
import {FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from "rxjs";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Produit } from 'src/app/model/produit';
import { ProduitService } from 'src/app/service/produit.service';
import { AddproduitComponent } from '../addproduit/addproduit.component';

@Component({
  selector: 'app-listproduit',
  templateUrl: './listproduit.component.html',
  styleUrls: ['./listproduit.component.scss']
})
export class ListproduitComponent implements OnInit {

  
  produit : Produit;
  constructor(public crudApi: ProduitService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddproduitComponent>,) { }
 
  ngOnInit() {
    this.getData();
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
  getData() {
   
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.produitList = response;}
     );
  }
  
  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this Produit ?')) {
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
  update(item : Produit ){
    this.crudApi.updatedataaaa(item.id,item).
    subscribe( data => { 
      this.router.navigate(['/']);

    });
  }

}
