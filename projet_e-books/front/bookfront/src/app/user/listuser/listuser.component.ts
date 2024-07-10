import { Component, OnInit, Inject } from '@angular/core';
import {FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from "rxjs";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';
import { AdduserComponent } from '../adduser/adduser.component';
@Component({
  selector: 'app-listuser',
  templateUrl: './listuser.component.html',
  styleUrls: ['./listuser.component.scss']
})
export class ListuserComponent implements OnInit {

  user : User;
  constructor(public crudApi: UserService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AdduserComponent>,) { }
 
  ngOnInit() {
    this.getData();
  }


  addUser()
  {
 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.height="90%";
    //dialogConfig.data="gdddd";
    this.matDialog.open(AdduserComponent, dialogConfig);
  }
  getData() {
   
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.userList = response;}
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
  selectData(item : User) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.height="90%";
    
    this.matDialog.open(AdduserComponent, dialogConfig);
     
     
  }

}
