import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FornecedorPjFormComponent } from './fornecedor-pj-form.component';

describe('FornecedorPjFormComponent', () => {
  let component: FornecedorPjFormComponent;
  let fixture: ComponentFixture<FornecedorPjFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FornecedorPjFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FornecedorPjFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
