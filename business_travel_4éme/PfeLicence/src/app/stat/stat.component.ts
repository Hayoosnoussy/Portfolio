import { Component, OnInit } from '@angular/core';
import { ChartType, ChartDataSets, ChartOptions, RadialChartOptions } from 'chart.js';
import { Label } from 'ng2-charts'; 
import { CompanyService } from '../service/company.service';
import { EmployeeService } from '../service/employee.service';
import { InvitationService } from '../service/invitation.service';

@Component({
  selector: 'app-stat',
  templateUrl: './stat.component.html',
  styleUrls: ['./stat.component.scss']
})
export class StatComponent implements OnInit {
inv : any =0;
emp : any=0;
com : any=0;
public radarChartData: ChartDataSets[] = [
  { data: [this.inv,this.emp, this.com, , 0], label: 'Statistiques Global' }
];
  constructor(public companyService: CompanyService, public employeeService: EmployeeService,
    public invitationService : InvitationService) { }

  ngOnInit(): void {
    this.getStatistique();
  }

async getCompany(){
  const promiseGet = await this.companyService.getStat().toPromise()
  return promiseGet;
}
async getEmployee(){
  const promiseGet = await this.employeeService.getStat().toPromise()
  return promiseGet;
}
async getInvitation(){
  const promiseGet = await this.invitationService.getStat().toPromise()
  return promiseGet;
}

async getStatistique(){
  await this.getCompany().then((res)=>{
    console.log('statistique',res)
    this.com = res
  });
  await this.getEmployee().then((res)=>{
    console.log('statistique',res)
    this.emp = res
  });
  await this.getInvitation().then((res)=>{
    console.log('statistique',res)
    this.inv = res
  });
  this.radarChartData  = [
    { data: [this.com-1,this.emp, this.inv, , 0], label: 'Statistiques Global' }
  ];
}


public radarChartOptions: RadialChartOptions = {
  responsive: true,
};
public radarChartLabels: Label[] = ['Nombre des entreprises', 'Nombre des employee ', 'Nombre des invitations '];


public radarChartType: ChartType = 'radar';


}
