import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InviwaitComponent } from './inviwait.component';

describe('InviwaitComponent', () => {
  let component: InviwaitComponent;
  let fixture: ComponentFixture<InviwaitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InviwaitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InviwaitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
