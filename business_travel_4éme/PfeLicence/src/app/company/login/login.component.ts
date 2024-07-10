import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CompanyService } from 'src/app/service/company.service';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: any={};
  usern : string;
  pwd : string;
  user1: any={};
  usern1 : string;
  pwd1 : string;
  
  constructor(private router:Router,private companyservice : CompanyService,private employeeservice : EmployeeService,public toastr: ToastrService) { }

  ngOnInit() {
    this.companyservice.islogin = false;
  }
  signin()
  {
    
    this.router.navigate(['/sign']);
  }
  reset()
  {
    
    this.router.navigate(['/reset']);
  }
 
  login() {
   
    this.companyservice.login(this.usern,this.pwd).subscribe(
      response =>{this.user = response;
        
      
       if (this.user.pwd == this.pwd)
       {
        this.companyservice.islogin = true;
        this.router.navigate(['/accueil']);
        localStorage.setItem('name', this.user.name);
        localStorage.setItem('role', this.user.roles);
        localStorage.setItem('prenom', this.user.lastName);
        localStorage.setItem('prenom', this.user.image);
        localStorage.setItem('prenom', this.user.login);
        localStorage.setItem('prenom', this.user.email);


        
 
       }
        else
        {
          this.toastr.show( 'Mot de Passe  Incorrecte ')
         }

    },
    error => 
    
      this.toastr.show( 'Login Incorrecte ')
   
    
    );


}



}
