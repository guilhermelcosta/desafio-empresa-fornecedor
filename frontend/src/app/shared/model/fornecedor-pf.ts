export class FornecedorPf {

  id: number = 0;
  nome: string;
  email: string;
  cpf: string;
  rg: string;
  cep: string;
  dataNascimento: string;

  constructor(nomeFantasia: string = '', email: string = '', cnpj: string = '', rg: string = '', cep: string = '', dataNascimento: string = '') {
    this.nome = nomeFantasia;
    this.email = email;
    this.cpf = cnpj;
    this.rg = rg;
    this.cep = cep;
    this.dataNascimento = dataNascimento;
  }
}
