import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioFormsComponent } from './usuario-forms.component';

describe('UsuarioFormsComponent', () => {
  let component: UsuarioFormsComponent;
  let fixture: ComponentFixture<UsuarioFormsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UsuarioFormsComponent]
    });
    fixture = TestBed.createComponent(UsuarioFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
