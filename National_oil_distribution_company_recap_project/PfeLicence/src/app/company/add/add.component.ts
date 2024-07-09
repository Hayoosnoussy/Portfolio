
import { Component, OnInit , Inject} from '@angular/core';
import {FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatDialogRef } from "@angular/material/dialog";
  import { ToastrService } from 'ngx-toastr';
  import { Router } from '@angular/router';
import { CompanyService } from 'src/app/service/company.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {

  constructor(public crudApi: CompanyService, public fb: FormBuilder, public toastr: ToastrService,
    private router : Router,
    @Inject(MAT_DIALOG_DATA)  public data,
    public dialogRef:MatDialogRef<AddComponent>,) { }
  
    ngOnInit(): void {
      if (this.crudApi.choixmenu == "A")
      {this.infoForm()};
    }
    infoForm() {
      this.crudApi.dataForm = this.fb.group({
        id: [''],
         
          name: ['', [Validators.required, Validators.minLength(2)]],
          address: ['', [Validators.required, Validators.minLength(8)]],
          
          roles: ['', [Validators.required]],  
          email: ['', [Validators.required]], 
          image: ['', [Validators.required]], 
          phone: ['', [Validators.required,Validators.minLength(8)]], 
          login: ['', [Validators.required, Validators.minLength(6)]],  
          pwd: ['', [Validators.required]],  
          });
      }
         
      ResetForm() {
        this.crudApi.dataForm.reset();
    }
    onSubmit() {
  
    if(this.crudApi.choixmenu == "A"){
  
      this.addData();
      this.router.navigate(['/listcompany']);
  
    }
    else {
      
      this.updateData();
      this.crudApi.choixmenu = "A";
  
    }
    
      
  }
    
  addData() {
    this.crudApi.createData(this.crudApi.dataForm.value).
    subscribe( data => {
      this.dialogRef.close();
      this.crudApi.getAll().subscribe(
        response =>{this.crudApi.companyList = response;}
       );
      this.router.navigate(['/listcompany']);
    });
  }
    updateData()
    {
      
      this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
      subscribe( data => {
        this.dialogRef.close();
        this.crudApi.getAll().subscribe(
          response =>{this.crudApi.companyList = response;}
         );
        this.router.navigate(['/listcompany']);
      });
    }
  

}
