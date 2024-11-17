import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/enviroment';
import {FornecedorPf} from '../model/fornecedor-pf';
import {converteDataYYYYMMDD} from '../util/data-util';

@Injectable({
  providedIn: 'root'
})
export class FornecedorPfService {


  constructor(private http: HttpClient) {
  }

  public buscarFornecedoresPf(indice: number = 0, itensPorPagina: number = 5): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/${environment.fornecedorPfRoute}?indice=${indice}&tamanho=${itensPorPagina}`);
  }

  public buscarFornecedoresPorCpf(cpf: string): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/${environment.fornecedorPfRoute}/cpf/${cpf}`);
  }

  public criarFornecedorPf(fornecedorPf: FornecedorPf): Observable<FornecedorPf> {
    fornecedorPf.dataNascimento = converteDataYYYYMMDD(new Date(fornecedorPf.dataNascimento));
    return this.http.post<FornecedorPf>(`${environment.apiUrl}/${environment.fornecedorPfRoute}`, fornecedorPf);
  }

  public atualizarFornecedorPf(fornecedorPf: FornecedorPf): Observable<FornecedorPf> {
    fornecedorPf.dataNascimento = converteDataYYYYMMDD(new Date(fornecedorPf.dataNascimento));
    return this.http.put<FornecedorPf>(`${environment.apiUrl}/${environment.fornecedorPfRoute}/${fornecedorPf.id}`, fornecedorPf);
  }

  public deletarFornecedorPf(fornecedorPf: FornecedorPf): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/${environment.fornecedorPfRoute}/${fornecedorPf.id}`);
  }
}
