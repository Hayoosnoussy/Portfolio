import { Component, OnInit , Inject} from '@angular/core';
import {FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatDialogRef } from "@angular/material/dialog";
  import { ToastrService } from 'ngx-toastr';
  import { Router } from '@angular/router';
import { ProduitService } from 'src/app/service/produit.service';
@Component({
  selector: 'app-addproduit',
  templateUrl: './addproduit.component.html',
  styleUrls: ['./addproduit.component.scss']
})
export class AddproduitComponent implements OnInit {
  public imagePath;
  imgURL: any;
  userFile ;
  val :string;
  t:string = 'New';
  error:any;

  constructor(public crudApi: ProduitService, public fb: FormBuilder, public toastr: ToastrService,
    private router : Router,
    @Inject(MAT_DIALOG_DATA)  public data,
    public dialogRef:MatDialogRef<AddproduitComponent>,) { }
  
    ngOnInit(): void {
      if (this.crudApi.choixmenu == "A")
      {this.infoForm()};
    }
    infoForm() {
      this.crudApi.dataForm = this.fb.group({
        id: [''],
        categorie: ['', [Validators.required, Validators.minLength(2)]],
          nom: ['', [Validators.required, Validators.minLength(5)]],
          prix: ['', [Validators.required]],
          vendeur : ['', [Validators.required, Validators.minLength(5)]],
          type : ['', [Validators.required, Validators.minLength(2)]],
          langue : ['', [Validators.required, Validators.minLength(2)]],
          statut : ['', [Validators.required]],
        
          });
      }
      get f() { return this.crudApi.dataForm.controls; }
      get nom(){return this.crudApi.dataForm.get('nom')}
      ResetForm() {
        this.crudApi.dataForm.reset();
    }
    onSubmit() {
  
    if(this.crudApi.choixmenu == "A"){
  
      this.addData();
  
    }
    else {
      
      this.updateData();
      this.crudApi.choixmenu = "A";
  
    }
    
      
  }
    
  onSelectFile(event) {
    if (event.target.files.length > 0)
    {
      const file = event.target.files[0];
      this.userFile = file;
     // this.f['profile'].setValue(file);
 
    var mimeType = event.target.files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.toastr.success( 'Only images are supported.');
      
      return;
    }
 
    var reader = new FileReader();
    
    this.imagePath = file;
    reader.readAsDataURL(file); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
    }
  }
      
      
    }

    addData() {
      const formData = new  FormData();
      const article = this.crudApi.dataForm.value;
      formData.append('produit',JSON.stringify(article));
      formData.append('file',this.userFile);
      this.crudApi.createData(formData).subscribe( data => {
        this.dialogRef.close();

        this.toastr.success('Ajout avec Success att  aprouve de Admin ');
        this.router.navigate(['/']); 
        this.crudApi.n = this.crudApi.n + 1 ;
        
      },err=> this.error = err["error"]["message"],);
    }
    updateData()
    {
      
      this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
      subscribe( data => {
        this.dialogRef.close();
        this.crudApi.getAll().subscribe(
          response =>{this.crudApi.produitList = response;}
         );
        this.router.navigate(['/']);
      });
    }
    onSelectCateg(categ: string)
    { 
      this.val=categ; 
    } 
  }
  