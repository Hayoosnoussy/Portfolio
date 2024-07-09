import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvidoneComponent } from './invidone.component';

describe('InvidoneComponent', () => {
  let component: InvidoneComponent;
  let fixture: ComponentFixture<InvidoneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvidoneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvidoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
