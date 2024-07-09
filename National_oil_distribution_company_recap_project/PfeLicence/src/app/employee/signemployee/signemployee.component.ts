import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-signemployee',
  templateUrl: './signemployee.component.html',
  styleUrls: ['./signemployee.component.scss']
})
export class SignemployeeComponent implements OnInit {
  constructor(public crudApi: EmployeeService, public fb: FormBuilder, public toastr: ToastrService,
    private router : Router,) { }

    

     
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

      get f(){return this.crudApi.dataForm.controls}

      ResetForm() {
        this.crudApi.dataForm.reset();
    }
    onSubmit() {
  
    if(this.crudApi.choixmenu == "A"){
  
      this.addData();
      this.router.navigate(['/loginloginemploye']);
  
    }
     
      
  }
    
  addData() {
    this.crudApi.createData(this.crudApi.dataForm.value).
    subscribe( data => {
       this.crudApi.getAll().subscribe(
        response =>{this.crudApi.employeeList = response;}
       );
      this.router.navigate(['/loginemploye']);
    });
  }
    
}
