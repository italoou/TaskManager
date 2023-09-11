import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskFormsComponent } from './task-forms.component';

describe('TaskFormsComponent', () => {
  let component: TaskFormsComponent;
  let fixture: ComponentFixture<TaskFormsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskFormsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaskFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
