import { Component, OnInit , Inject} from '@angular/core';
import {FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatDialogRef } from "@angular/material/dialog";
  import { ToastrService } from 'ngx-toastr';
  import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { AdduserComponent } from '../user/adduser/adduser.component';
@Component({
  selector: 'app-sign',
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.scss']
})
export class SignComponent implements OnInit {

  error:any;
  constructor(public crudApi: UserService, public fb: FormBuilder, public toastr: ToastrService,
    private router : Router,
    @Inject(MAT_DIALOG_DATA)  public data,
    public dialogRef:MatDialogRef<AdduserComponent>,) { }

    ngOnInit(): void {
      if (this.crudApi.choixmenu == "A")
      {this.infoForm()};
    }
    infoForm() {
      this.crudApi.dataForm = this.fb.group({
        id: [''],
        nom: ['', [Validators.required, Validators.minLength(8)]],

          ville: ['', [Validators.required, Validators.minLength(3)]],
          codep: ['', [Validators.required, Validators.minLength(3)]],
          email : ['', [Validators.required, Validators.minLength(8)]],
          adresse : ['', [Validators.required, Validators.minLength(8)]],
          role : ['', [Validators.required, Validators.minLength(3)]],
          login: ['', [Validators.required]],  
          pwd: ['', [Validators.required]],  
          });
      }
         
      ResetForm() {
        this.crudApi.dataForm.reset();
    }

    get f() { return this.crudApi.dataForm.controls; }
    get nom(){return this.crudApi.dataForm.get('nom')}
    onSubmit() {
  
    if(this.crudApi.choixmenu == "A"){
  
      this.addData();
      
  
    }
    else {
      
      this.updateData();
      this.crudApi.choixmenu = "A";
  
    }
    
      
  }
    
  addData() {
    this.crudApi.createData(this.crudApi.dataForm.value).
    subscribe( data => {
      this.crudApi.getAll().subscribe(
        response =>{this.crudApi.userList = response }
          );
          this.router.navigate(['/login']); 

          },err=> this.error = err["error"]["message"],);
        }

      
    updateData()
    {
      
      this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
      subscribe( data => {
        this.dialogRef.close();
        this.crudApi.getAll().subscribe(
          response =>{this.crudApi.userList = response;}
         );
        this.router.navigate(['/']);
      });
    }
  
}
