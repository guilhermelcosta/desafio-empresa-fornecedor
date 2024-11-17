import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeletorFornecedorComponent } from './seletor-fornecedor.component';

describe('SeletorFornecedorComponent', () => {
  let component: SeletorFornecedorComponent;
  let fixture: ComponentFixture<SeletorFornecedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeletorFornecedorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeletorFornecedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
