import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignemployeeComponent } from './signemployee.component';

describe('SignemployeeComponent', () => {
  let component: SignemployeeComponent;
  let fixture: ComponentFixture<SignemployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignemployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignemployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
