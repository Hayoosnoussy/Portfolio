import { Component, OnInit , Inject} from '@angular/core';
import {FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatDialogRef } from "@angular/material/dialog";
  import { ToastrService } from 'ngx-toastr';
  import { Router } from '@angular/router';
import { CompanyService } from 'src/app/service/company.service';
import { EmployeeService } from 'src/app/service/employee.service';
import { InvitationService } from 'src/app/service/invitation.service';

@Component({
  selector: 'app-addinvitation',
  templateUrl: './addinvitation.component.html',
  styleUrls: ['./addinvitation.component.scss']
})
export class AddinvitationComponent implements OnInit {

  constructor(public crudApi: InvitationService, public fb: FormBuilder, public toastr: ToastrService,
    private router : Router,
    @Inject(MAT_DIALOG_DATA)  public data,
    public dialogRef:MatDialogRef<AddinvitationComponent>,) { }
  
    ngOnInit(): void {
      if (this.crudApi.choixmenu == "A")
      {this.infoForm()};
    }
    infoForm() {
      this.crudApi.dataForm = this.fb.group({
        id: [''],
         
          date: ['', [Validators.required]],
          destinataireEmail: ['', [Validators.required]],
          sentStatus: ['',[Validators.required]],
         
          });
      }
         
      ResetForm() {
        this.crudApi.dataForm.reset();
    }
    onSubmit() {
  
    if(this.crudApi.choixmenu == "A"){
  
      this.addData();
      this.router.navigate(['/listinvitation']);
  
    }
    
    
      
  }
    
  addData() {
    this.crudApi.createData(this.crudApi.dataForm.value).
    subscribe( data => {
      this.dialogRef.close();
      this.crudApi.getAll().subscribe(
        response =>{this.crudApi.invitationList = response;}
       );
      this.router.navigate(['/listinvitation']);
    });
  }
   
  

}
