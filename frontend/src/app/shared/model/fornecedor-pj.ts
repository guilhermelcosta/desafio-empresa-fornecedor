export class FornecedorPj {

  id: number = 0;
  nomeFantasia: string;
  email: string;
  cnpj: string;
  cep: string;

  constructor(nomeFantasia: string = '', email: string = '', cnpj: string = '', cep: string = '') {
    this.nomeFantasia = nomeFantasia;
    this.email = email;
    this.cnpj = cnpj;
    this.cep = cep;
  }
}
