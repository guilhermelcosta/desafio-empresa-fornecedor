import { TestBed } from '@angular/core/testing';

import { FornecedorPfService } from './fornecedor-pf.service';

describe('FornecedorPfService', () => {
  let service: FornecedorPfService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FornecedorPfService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
