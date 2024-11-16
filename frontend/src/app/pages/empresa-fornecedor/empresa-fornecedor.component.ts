import {Component} from '@angular/core';
import {MatTab, MatTabGroup} from '@angular/material/tabs';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {EmpresaComponent} from '../../shared/tabs/empresa/empresa.component';
import {FornecedorPjComponent} from '../../shared/tabs/fornecedor-pj/fornecedor-pj.component';
import {FornecedorPfComponent} from '../../shared/tabs/fornecedor-pf/fornecedor-pf.component';

@Component({
  selector: 'app-empresa-fornecedor',
  standalone: true,
  imports: [
    MatButtonToggleModule,
    MatTabGroup,
    MatTab,
    EmpresaComponent,
    FornecedorPjComponent,
    FornecedorPfComponent
  ],
  templateUrl: './empresa-fornecedor.component.html',
  styleUrl: './empresa-fornecedor.component.css'
})
export class EmpresaFornecedorComponent {

}
