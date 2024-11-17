import {Component, OnInit} from '@angular/core';
import {MatTab, MatTabGroup} from '@angular/material/tabs';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {EmpresaComponent} from '../../shared/tabs/empresa/empresa.component';
import {FornecedorPfComponent} from '../../shared/tabs/fornecedor-pf/fornecedor-pf.component';
import {NgIf} from '@angular/common';
import {FornecedorPjComponent} from '../../shared/tabs/fornecedor-pj/fornecedor-pj.component';
import {TipoFornecedor} from '../../shared/enum/tipo-fornecedor';

@Component({
  selector: 'app-empresa-fornecedor',
  standalone: true,
  imports: [
    MatButtonToggleModule,
    MatTabGroup,
    MatTab,
    EmpresaComponent,
    FornecedorPfComponent,
    NgIf,
    FornecedorPjComponent
  ],
  templateUrl: './empresa-fornecedor.component.html',
  styleUrl: './empresa-fornecedor.component.css'
})
export class EmpresaFornecedorComponent implements OnInit {


  public tipoFornecedor!: TipoFornecedor;
  protected readonly TipoFornecedor = TipoFornecedor;

  public ngOnInit(): void {
    this.tipoFornecedor = TipoFornecedor.PJ;
  }

  public atualizarTipoFornecedorSelecionado(tipoFornecedor: TipoFornecedor) {
    this.tipoFornecedor = tipoFornecedor;
  }
}
