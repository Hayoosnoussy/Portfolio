import { Component, OnInit, Inject } from '@angular/core';
import {FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from "rxjs";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Company } from 'src/app/model/company';
import { CompanyService } from 'src/app/service/company.service';
import { Employee } from 'src/app/model/employee';

import { EmployeeService } from 'src/app/service/employee.service';
import { Invitation } from 'src/app/model/invitation';
import { AddinvitationComponent } from '../addinvitation/addinvitation.component';
import { InvitationService } from 'src/app/service/invitation.service';

@Component({
  selector: 'app-inviwait',
  templateUrl: './inviwait.component.html',
  styleUrls: ['./inviwait.component.scss']
})
export class InviwaitComponent implements OnInit {

  constructor(public crudApi: InvitationService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddinvitationComponent>,) { }


  ngOnInit(): void {
  }
  getData1() {
   
    this.crudApi.getWait().subscribe(
      response =>{this.crudApi.invitationList = response;}
     );
  }

}
