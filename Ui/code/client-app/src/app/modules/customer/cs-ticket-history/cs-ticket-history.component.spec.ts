import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CsTicketHistoryComponent } from './cs-ticket-history.component';

describe('CsTicketHistoryComponent', () => {
  let component: CsTicketHistoryComponent;
  let fixture: ComponentFixture<CsTicketHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CsTicketHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CsTicketHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
