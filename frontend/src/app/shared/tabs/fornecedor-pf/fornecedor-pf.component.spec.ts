import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FornecedorPfComponent } from './fornecedor-pf.component';

describe('FornecedorPfComponent', () => {
  let component: FornecedorPfComponent;
  let fixture: ComponentFixture<FornecedorPfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FornecedorPfComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FornecedorPfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
