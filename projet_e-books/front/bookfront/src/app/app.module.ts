import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatDialogModule,MatDialogRef, } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon'; 
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { RouterModule, Routes} from '@angular/router';
import { ToastrModule } from 'ngx-toastr'; 
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { MatSliderModule } from '@angular/material/slider';
import { AppComponent } from './app.component';
import { AddproduitComponent } from './produit/addproduit/addproduit.component';
import { ListproduitComponent } from './produit/listproduit/listproduit.component';
import { AdduserComponent } from './user/adduser/adduser.component';
import { ListuserComponent } from './user/listuser/listuser.component';
import { MenuComponent } from './menu/menu.component';
import { LoginComponent } from './login/login.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import { ProfilComponent } from './profil/profil.component';
import { SignComponent } from './sign/sign.component';
import { StatComponent } from './stat/stat.component';
import { ChartsModule } from 'ng2-charts';
import { CarouselComponent } from './carousel/carousel.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


const MATERIAL_MODULES = [MatToolbarModule,
  MatIconModule
]; 
const appRoutes : Routes = [
  {path: '', component: MenuComponent},
    {path: 'carousel', component: CarouselComponent},

  {path: 'user', component: AdduserComponent},
  {path: 'login', component: LoginComponent},
  {path: 'acceuil', component: AcceuilComponent},
  {path: 'sign', component: SignComponent},
  {path: 'stat', component: StatComponent},

  {path: 'listuser', component: ListuserComponent},
  {path: 'produit', component: AddproduitComponent},
  {path: 'listproduit', component: ListproduitComponent},
  {path: 'profil', component: ProfilComponent},




]
@NgModule({
  declarations: [
    AppComponent,
    AddproduitComponent,
    ListproduitComponent,
    AdduserComponent,
    ListuserComponent,
    MenuComponent,
    LoginComponent,
    AcceuilComponent,
    ProfilComponent,
    SignComponent,
    StatComponent,
    CarouselComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSliderModule,
    MatToolbarModule,
    MatIconModule,
    FlexLayoutModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    ToastrModule.forRoot(),
    ChartsModule,
    NgbModule
  ],
  
  exports : MATERIAL_MODULES,
  providers: [{ provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {}  }, ],
  bootstrap: [AppComponent],
})
export class AppModule { }
