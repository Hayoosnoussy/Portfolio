import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompanyService } from '../service/company.service';
import { EmployeeService } from '../service/employee.service';
import { InvitationService } from '../service/invitation.service';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit {
  name : String;
  prenom : String;
  role:String;
  miss : any =0;
msg : any=0;
frez : any=0;
  constructor(public companyService : CompanyService,private router : Router,
    public employeeService : EmployeeService
    ,public invitationService : InvitationService ,) { }

  ngOnInit(): void {
    this.name = localStorage.getItem('name');

  }

}
