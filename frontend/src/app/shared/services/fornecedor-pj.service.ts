import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/enviroment';
import {FornecedorPj} from '../model/fornecedor-pj';

@Injectable({
  providedIn: 'root'
})
export class FornecedorPjService {

  constructor(private http: HttpClient) {
  }

  public buscarFornecedores(indice: number = 0, itensPorPagina: number = 5): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/${environment.fornecedorPjRoute}?indice=${indice}&tamanho=${itensPorPagina}`);
  }

  public criarFornecedorPj(fornecedorPj: FornecedorPj): Observable<FornecedorPj> {
    return this.http.post<FornecedorPj>(`${environment.apiUrl}/${environment.fornecedorPjRoute}`, fornecedorPj);
  }

  public atualizarFornecedorPj(fornecedorPj: FornecedorPj): Observable<FornecedorPj> {
    return this.http.put<FornecedorPj>(`${environment.apiUrl}/${environment.fornecedorPjRoute}/${fornecedorPj.id}`, fornecedorPj);
  }

  public deletarFornecedorPj(fornecedorPj: FornecedorPj): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/${environment.fornecedorPjRoute}/${fornecedorPj.id}`);
  }
}
