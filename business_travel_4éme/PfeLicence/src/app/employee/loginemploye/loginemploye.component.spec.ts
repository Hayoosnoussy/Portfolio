import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginemployeComponent } from './loginemploye.component';

describe('LoginemployeComponent', () => {
  let component: LoginemployeComponent;
  let fixture: ComponentFixture<LoginemployeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginemployeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginemployeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
