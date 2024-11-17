import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaFornecedorEmpresaComponent } from './lista-fornecedor-empresa.component';

describe('ListaFornecedorEmpresaComponent', () => {
  let component: ListaFornecedorEmpresaComponent;
  let fixture: ComponentFixture<ListaFornecedorEmpresaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaFornecedorEmpresaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaFornecedorEmpresaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
