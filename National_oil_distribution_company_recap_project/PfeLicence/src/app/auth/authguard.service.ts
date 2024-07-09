import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { CompanyService } from '../service/company.service';
import { EmployeeService } from '../service/employee.service';
@Injectable({
  providedIn: 'root'
})
export class AuthguardService {

  constructor(private auth:CompanyService,private auth1:EmployeeService,private router : Router){

  }
  canActivate(
    next: ActivatedRouteSnapshot,

    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
 
 
      if ((this.auth.islogin == false) && (this.auth1.islogin == false))
    { this.router.navigate(['/login']);
    localStorage.clear();
      return false;

    }
    else
    {
     return true;
    }
 
    
    }
    
  }
