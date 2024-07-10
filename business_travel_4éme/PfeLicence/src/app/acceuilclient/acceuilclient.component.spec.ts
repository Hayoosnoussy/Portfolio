import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceuilclientComponent } from './acceuilclient.component';

describe('AcceuilclientComponent', () => {
  let component: AcceuilclientComponent;
  let fixture: ComponentFixture<AcceuilclientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcceuilclientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceuilclientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
