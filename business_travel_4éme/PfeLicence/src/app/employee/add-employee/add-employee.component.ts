import { Component, OnInit , Inject} from '@angular/core';
import {FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatDialogRef } from "@angular/material/dialog";
  import { ToastrService } from 'ngx-toastr';
  import { Router } from '@angular/router';
import { CompanyService } from 'src/app/service/company.service';
import { EmployeeService } from 'src/app/service/employee.service';


@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.scss']
})
export class AddEmployeeComponent implements OnInit {

  
  constructor(public crudApi: EmployeeService, public fb: FormBuilder, public toastr: ToastrService,
    private router : Router,
    @Inject(MAT_DIALOG_DATA)  public data,
    public dialogRef:MatDialogRef<AddEmployeeComponent>,) { }
  
    ngOnInit(): void {
      if (this.crudApi.choixmenu == "A")
      {this.infoForm()};
    }
    infoForm() {
      this.crudApi.dataForm = this.fb.group({
        id: [''],
         
          name: ['', [Validators.required]],
          lastname: ['', [Validators.required]],
          birthday: ['',[Validators.required]],
          phone: ['', [Validators.required, Validators.minLength(8)]], 
          image: ['', [Validators.required]], 
          email: ['', [Validators.required]], 

          roles: ['', [Validators.required]], 
          
 
          login: ['', [Validators.required,Validators.minLength(6)]],  
          pwd: ['', [Validators.required]],  
          });
      }
         
      ResetForm() {
        this.crudApi.dataForm.reset();
    }
    onSubmit() {
  
    if(this.crudApi.choixmenu == "A"){
  
      this.addData();
      this.router.navigate(['/listemployee']);
  
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
        response =>{this.crudApi.employeeList = response;}
       );
      this.router.navigate(['/listemployee']);
    });
  }
    updateData()
    {
      
      this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
      subscribe( data => {
        this.dialogRef.close();
        this.crudApi.getAll().subscribe(
          response =>{this.crudApi.employeeList = response;}
         );
        this.router.navigate(['/listemployee']);
      });
    }
  

}
