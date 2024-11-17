import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FornecedorPfFormComponent } from './fornecedor-pf-form.component';

describe('FornecedorPfFormComponent', () => {
  let component: FornecedorPfFormComponent;
  let fixture: ComponentFixture<FornecedorPfFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FornecedorPfFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FornecedorPfFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
