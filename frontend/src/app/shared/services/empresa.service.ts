import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Empresa} from '../model/empresa';
import {environment} from '../../environments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  constructor(private http: HttpClient) {
  }

  public buscarEmpresas(indice: number = 0, itensPorPagina: number = 5): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/${environment.empresaRoute}?indice=${indice}&tamanho=${itensPorPagina}`);
  }

  public buscarEmpresaPorId(idEmpresa: number): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/${environment.empresaRoute}/${idEmpresa}`);
  }

  public criarEmpresa(empresa: Empresa): Observable<Empresa> {
    return this.http.post<Empresa>(`${environment.apiUrl}/${environment.empresaRoute}`, empresa);
  }

  public atualizarEmpresa(empresa: Empresa): Observable<Empresa> {
    return this.http.put<Empresa>(`${environment.apiUrl}/${environment.empresaRoute}/${empresa.id}`, empresa);
  }

  public atualizarListaFornecedoresPf(listaIds: number[], empresa: Empresa): Observable<any> {
    console.log("atualizarListaFornecedoresPf")
    console.log(listaIds);
    return this.http.put<any>(`${environment.apiUrl}/${environment.empresaRoute}/${empresa.id}/fornecedor-pf`, listaIds);
  }

  public atualizarListaFornecedoresPj(listaIds: number[], empresa: Empresa): Observable<any> {
    console.log("atualizarListaFornecedoresPj")
    console.log(listaIds);
    console.log(`${environment.apiUrl}/${environment.empresaRoute}/${empresa.id}/fornecedor-pj`)
    return this.http.put<any>(`${environment.apiUrl}/${environment.empresaRoute}/${empresa.id}/fornecedor-pj`, listaIds);
  }

  public deletarEmpresa(empresa: Empresa): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/${environment.empresaRoute}/${empresa.id}`);
  }
}
