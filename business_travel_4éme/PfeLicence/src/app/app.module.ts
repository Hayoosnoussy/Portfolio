import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatDialogModule,MatDialogRef, } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { RouterModule, Routes} from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ChartsModule } from 'ng2-charts';






import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddComponent } from './company/add/add.component';
import { ListComponent } from './company/list/list.component';
import { ListEmployeeComponent } from './employee/list-employee/list-employee.component';
import { AddEmployeeComponent } from './employee/add-employee/add-employee.component';
import { AddinvitationComponent } from './invitation/addinvitation/addinvitation.component';
import { ListinvitationComponent } from './invitation/listinvitation/listinvitation.component';
import { AccueilComponent } from './accueil/accueil.component';
import { AcceuilclientComponent } from './acceuilclient/acceuilclient.component';
import { LoginComponent } from './company/login/login.component';
 
import { AuthguardService } from './auth/authguard.service';
import { LoginemployeComponent } from './employee/loginemploye/loginemploye.component';
import { StatComponent } from './stat/stat.component';
import { SignComponent } from './company/sign/sign.component';
import { SignemployeeComponent } from './employee/signemployee/signemployee.component';


const MATERIAL_MODULES = [MatToolbarModule,
  MatIconModule
]; 

const appRoutes : Routes = [
  {path:'accueil',component:AccueilComponent ,canActivate:[AuthguardService], children : [
  {path: 'company', component: AddComponent},
  {path: 'listcompany', component: ListComponent},
  {path: 'listemployee', component: ListEmployeeComponent},
  {path: 'employee', component: AddEmployeeComponent},
  {path: 'listinvi', component: ListinvitationComponent},
  {path: 'invi', component: AddinvitationComponent},
  {path: 'statistique', component: StatComponent},
 


]},

{path: 'logincompany' , component:LoginComponent},
{path: 'loginemploye' , component:LoginemployeComponent},
//{path: 'sign', component: SignComponent},
//{path: 'reset', component: AddNotificationComponent},

 




];

@NgModule({
  declarations: [
    AppComponent,
    
    AddComponent,
    ListComponent,
    ListEmployeeComponent,
    AddEmployeeComponent,
    AddinvitationComponent,
    ListinvitationComponent,
    AccueilComponent,
    AcceuilclientComponent,
    LoginComponent,
    LoginemployeComponent,
    StatComponent,
    SignComponent,
    SignemployeeComponent,

    
    
    
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatDialogModule,
    FlexLayoutModule,
    MatToolbarModule,
    MatIconModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule,
    ChartsModule,
    RouterModule.forRoot(appRoutes),
    ToastrModule.forRoot()
  ],
  exports : MATERIAL_MODULES,
  providers: [{ provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {}  }, ],
  bootstrap: [AppComponent],
  entryComponents: [ ],
})
export class AppModule { }
