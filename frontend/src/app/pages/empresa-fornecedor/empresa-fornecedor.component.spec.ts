import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpresaFornecedorComponent } from './empresa-fornecedor.component';

describe('EmpresaFornecedorComponent', () => {
  let component: EmpresaFornecedorComponent;
  let fixture: ComponentFixture<EmpresaFornecedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpresaFornecedorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpresaFornecedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
