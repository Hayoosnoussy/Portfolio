import { Component, OnInit } from '@angular/core';
import { ChartType } from 'chart.js';
import { Color, Label, MultiDataSet } from 'ng2-charts';
import { ProduitService } from '../service/produit.service';


@Component({
  selector: 'app-stat',
  templateUrl: './stat.component.html',
  styleUrls: ['./stat.component.scss']
})
export class StatComponent implements OnInit {
  z: any=0;
  a: any=0;
  e: any=0;
  nb: any=0;
  k: any=0;
  doughnutChartData: MultiDataSet = [
    [this.a,this.z, this.e, this.nb, this.k]
  ];
 

  constructor( public crudApi: ProduitService,) { }

  ngOnInit(): void {
    this.getData();

  }
  async getAlim() {
    const promiseGet= await this.crudApi.getStat().toPromise()
     return promiseGet;
    
   }
   async getAlime() {
    const promiseGet= await this.crudApi.getState().toPromise()
     return promiseGet;
    
   }
   async getAlimr() {
    const promiseGet= await this.crudApi.getStatr().toPromise()
     return promiseGet;
    
   }
   async getAlimt() {
    const promiseGet= await this.crudApi.getStatt().toPromise()
     return promiseGet;
    
   }
   async getAlimy() {
    const promiseGet= await this.crudApi.getStaty().toPromise()
     return promiseGet;
    
   }
   async getData(){
    await this.getAlim().then((res)=>{
      this.a = res
    });
    await this.getAlime().then((res)=>{
      this.z = res
    });
    await this.getAlimr().then((res)=>{
      this.e = res
    });
    await this.getAlimt().then((res)=>{
      this.nb = res
    });
    await this.getAlimy().then((res)=>{
      this.k = res
    });
    this.doughnutChartData = [
      [this.a,  this.z, this.e, this.nb, this.k]
    ];
    
  }
  doughnutChartLabels: Label[] = ['Fiction', 'Non Fiction', 'Enfant & adolescent','Éducation','Papeterie cadeaux'];
 
  doughnutChartType: ChartType = 'doughnut';
  public doughnutChartColors: Color[] = [
   
    {backgroundColor:['rgba(17, 190, 176, 0.842)','rgba(56, 79, 211, 0.26)','rgb(240, 236, 6)','rgb(135, 90, 202)','rgb(202, 90, 109)']},
    
  ];
  public aa: Color[] = [
   
    {backgroundColor:['rgb(113, 202, 90)','rgb(113, 202, 90)','rgb(113, 202, 90)','rgb(113, 202, 90)','rgb(113, 202, 90)',]},
    
  ];



}
