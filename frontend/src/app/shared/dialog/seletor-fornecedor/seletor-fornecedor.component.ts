import {Component, Inject, OnInit} from '@angular/core';
import {TipoFornecedor} from '../../enum/tipo-fornecedor';
import {MatButtonToggle, MatButtonToggleGroup} from '@angular/material/button-toggle';
import {FornecedorPfService} from '../../services/fornecedor-pf.service';
import {FornecedorPjService} from '../../services/fornecedor-pj.service';
import {FornecedorPf} from '../../model/fornecedor-pf';
import {FornecedorPj} from '../../model/fornecedor-pj';
import {MatListOption, MatSelectionList} from '@angular/material/list';
import {NgForOf} from '@angular/common';
import {MatButton} from '@angular/material/button';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {EmpresaService} from '../../services/empresa.service';
import {Empresa} from '../../model/empresa';

@Component({
  selector: 'app-seletor-fornecedor',
  standalone: true,
  imports: [
    MatButtonToggle,
    MatButtonToggleGroup,
    MatSelectionList,
    MatListOption,
    NgForOf,
    MatButton
  ],
  templateUrl: './seletor-fornecedor.component.html',
  styleUrl: './seletor-fornecedor.component.css'
})
export class SeletorFornecedorComponent implements OnInit {

  public fornecedores: (FornecedorPf | FornecedorPj)[] = [];
  public tipoFornecedorSelecionado: TipoFornecedor = TipoFornecedor.PJ;
  protected readonly TipoFornecedor = TipoFornecedor;
  protected readonly FornecedorPj = FornecedorPj;

  constructor(private fornecedorPfService: FornecedorPfService,
              private fornecedorPjService: FornecedorPjService,
              private empresaService: EmpresaService,
              private dialogRef: MatDialogRef<SeletorFornecedorComponent>,
              @Inject(MAT_DIALOG_DATA) public empresa: Empresa) {
  }

  ngOnInit(): void {
    this.buscarFornecedores();
  }

  public buscarFornecedores(): void {
    if (this.tipoFornecedorSelecionado === TipoFornecedor.PJ) {
      this.fornecedorPjService.buscarFornecedoresPj().subscribe(
        (dados: any) => {
          this.fornecedores = dados.content;
        }
      );
    } else {
      this.fornecedorPfService.buscarFornecedoresPf().subscribe(
        (dados: any) => {
          this.fornecedores = dados.content;
        }
      );
    }
  }

  public aoMudarTipoFornecedor(tipo: TipoFornecedor): void {
    this.tipoFornecedorSelecionado = tipo;
    this.buscarFornecedores();
  }

  public construirNome(fornecedor: FornecedorPf | FornecedorPj) {
    return this.tipoFornecedorSelecionado === TipoFornecedor.PJ
      ? (fornecedor as FornecedorPj).nomeFantasia
      : (fornecedor as FornecedorPf).nome;
  }

  public aoConfirmar(matListOptions: MatListOption[]) {

    const idsSelecionados = matListOptions.map(f => f.value.id);
    const idsExistentes = this.tipoFornecedorSelecionado === TipoFornecedor.PJ
      ? this.empresa.fornecedoresPJ.map(f => f.id)
      : this.empresa.fornecedoresPF.map(f => f.id);

    const idsCombinados = [...new Set([...idsExistentes, ...idsSelecionados])];

    this.tipoFornecedorSelecionado === TipoFornecedor.PJ
      ? this.empresaService.atualizarListaFornecedoresPj(idsCombinados, this.empresa).subscribe((dados) => console.log(dados))
      : this.empresaService.atualizarListaFornecedoresPf(idsCombinados, this.empresa).subscribe((dados) => console.log(dados));

    this.dialogRef.close();
  }

  public aoCancelar() {
    this.dialogRef.close();
  }
}
