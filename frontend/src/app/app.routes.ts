import {Routes} from '@angular/router';
import {ListaFornecedorEmpresaComponent} from './pages/lista-fornecedor-empresa/lista-fornecedor-empresa.component';
import {EmpresaFornecedorComponent} from './pages/empresa-fornecedor/empresa-fornecedor.component';

export const routes: Routes = [
  {path: '', redirectTo: 'empresa-fornecedor', pathMatch: 'full'},
  {path: 'empresa-fornecedor', component: EmpresaFornecedorComponent},
  {path: 'lista-fornecedor-empresa/:id', component: ListaFornecedorEmpresaComponent}
];
