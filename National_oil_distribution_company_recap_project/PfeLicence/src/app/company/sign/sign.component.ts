import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { CompanyService } from 'src/app/service/company.service';

@Component({
  selector: 'app-sign',
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.scss']
})
export class SignComponent implements OnInit {

  constructor(public crudApi: CompanyService, public fb: FormBuilder, public toastr: ToastrService,
    private router : Router,) { }

    

     
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

      get f(){return this.crudApi.dataForm.controls}

      ResetForm() {
        this.crudApi.dataForm.reset();
    }
    onSubmit() {
  
    if(this.crudApi.choixmenu == "A"){
  
      this.addData();
      this.router.navigate(['/logincompany']);
  
    }
     
      
  }
    
  addData() {
    this.crudApi.createData(this.crudApi.dataForm.value).
    subscribe( data => {
       this.crudApi.getAll().subscribe(
        response =>{this.crudApi.companyList = response;}
       );
      this.router.navigate(['/logincompany']);
    });
  }
    
}