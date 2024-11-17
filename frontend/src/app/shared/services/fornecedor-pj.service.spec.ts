import { TestBed } from '@angular/core/testing';

import { FornecedorPjService } from './fornecedor-pj.service';

describe('FornecedorService', () => {
  let service: FornecedorPjService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FornecedorPjService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
