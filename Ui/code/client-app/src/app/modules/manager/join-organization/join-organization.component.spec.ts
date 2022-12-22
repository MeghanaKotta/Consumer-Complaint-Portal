import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinOrganizationComponent } from './join-organization.component';

describe('JoinOrganizationComponent', () => {
  let componenit: JoinOrganizationComponent;
  let fixture: ComponentFixture<JoinOrganizationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JoinOrganizationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JoinOrganizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
