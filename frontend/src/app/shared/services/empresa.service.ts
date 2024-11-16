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

  public buscarEmpresas(): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/${environment.empresaRoute}`);
  }

  public criarEmpresa(empresa: Empresa): Observable<Empresa> {
    return this.http.post<Empresa>(`${environment.apiUrl}/${environment.empresaRoute}`, empresa);
  }
}
