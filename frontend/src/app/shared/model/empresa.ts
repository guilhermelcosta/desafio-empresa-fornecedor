import {FornecedorPj} from './fornecedor-pj';
import {FornecedorPf} from './fornecedor-pf';

export class Empresa {

  id: number = 0;
  nomeFantasia: string;
  email: string;
  cnpj: string;
  cep: string;
  fornecedoresPJ: FornecedorPj[];
  fornecedoresPF: FornecedorPf[];

  constructor(nomeFantasia: string = '', email: string = '', cnpj: string = '', cep: string = '', fornecedoresPJ: FornecedorPj[] = [], fornecedoresPF: FornecedorPf[] = []) {
    this.nomeFantasia = nomeFantasia;
    this.email = email;
    this.cnpj = cnpj;
    this.cep = cep;
    this.fornecedoresPJ = fornecedoresPJ;
    this.fornecedoresPF = fornecedoresPF;
  }
}
