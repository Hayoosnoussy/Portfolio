import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  annee : number = 0;  

  user: any={};
    usern : string;

  
    pwd : string;
    constructor( private router:Router,private userservice : UserService,public toastr: ToastrService) { }
  
    ngOnInit() {
      this.userservice.islogin = false;
    }
  
    signin()
    {
      
      this.router.navigate(['/sign']);
    }
    
    login() {
     
      this.userservice.login(this.usern,this.pwd).subscribe(
        response =>{this.user = response;
          
        
         if (this.user.pwd == this.pwd)
         {
          this.userservice.islogin = true;
          localStorage.setItem('name', this.user.nom);
          localStorage.setItem('login', this.user.login);
          localStorage.setItem('pwd', this.user.pwd);
          localStorage.setItem('role', this.user.role);
          localStorage.setItem('email', this.user.email);
          localStorage.setItem('id', this.user.id);
  
          this.userservice.islogin = true;
          if (this.user.role  == "Admin")
           {
            this.userservice.admin = true; 
            this.router.navigate(['/']);       
           }
            else if  (this.user.role  == "Client")
        {
          this.router.navigate(['/']);
        
        }
        else if  (this.user.role  == "Book")
        {
          this.router.navigate(['/']);
        
        }
  
         }
          else
          {
            this.toastr.error( 'Mot De Passe Incorrect ')
           }
  
      },
      error => 
      
        this.toastr.info( 'Login Incorrect ')
     
      
      );
   
  
  }
  }