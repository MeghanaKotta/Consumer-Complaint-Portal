import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOrgMemberComponent } from './add-org-member.component';

describe('AddOrgMemberComponent', () => {
  let component: AddOrgMemberComponent;
  let fixture: ComponentFixture<AddOrgMemberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOrgMemberComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddOrgMemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
