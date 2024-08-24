import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmineTemplateComponent } from './admine-template.component';

describe('AdmineTemplateComponent', () => {
  let component: AdmineTemplateComponent;
  let fixture: ComponentFixture<AdmineTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdmineTemplateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmineTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
