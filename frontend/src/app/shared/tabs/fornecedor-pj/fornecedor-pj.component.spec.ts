import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FornecedorPjComponent } from './fornecedor-pj.component';

describe('FornecedorPjComponent', () => {
  let component: FornecedorPjComponent;
  let fixture: ComponentFixture<FornecedorPjComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FornecedorPjComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FornecedorPjComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
