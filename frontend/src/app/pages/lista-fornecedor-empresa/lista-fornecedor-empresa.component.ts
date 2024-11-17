import {Component, OnInit, ViewChild} from '@angular/core';
import {FornecedorPjService} from '../../shared/services/fornecedor-pj.service';
import {ActivatedRoute} from '@angular/router';
import {FornecedorPfService} from '../../shared/services/fornecedor-pf.service';
import {EmpresaService} from '../../shared/services/empresa.service';
import {Empresa} from '../../shared/model/empresa';
import {CepPipe} from '../../shared/pipes/cep.pipe';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable,
  MatTableDataSource
} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, MatSortHeader} from '@angular/material/sort';
import {CpfPipe} from '../../shared/pipes/cpf.pipe';
import {DataDdmmyyyyPipe} from '../../shared/pipes/data-ddmmyyyy.pipe';
import {FornecedorPf} from '../../shared/model/fornecedor-pf';
import {CnpjPipe} from '../../shared/pipes/cnpj.pipe';
import {FornecedorPj} from '../../shared/model/fornecedor-pj';
import {MatIcon} from '@angular/material/icon';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import {SeletorFornecedorComponent} from '../../shared/dialog/seletor-fornecedor/seletor-fornecedor.component';

@Component({
  selector: 'app-lista-fornecedor-empresa',
  standalone: true,
  imports: [
    CepPipe,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRow,
    MatRowDef,
    MatSort,
    MatSortHeader,
    MatTable,
    MatHeaderCellDef,
    CpfPipe,
    DataDdmmyyyyPipe,
    MatPaginator,
    CnpjPipe,
    MatIcon,
    MatIconButton,
    MatButton
  ],
  templateUrl: './lista-fornecedor-empresa.component.html',
  styleUrl: './lista-fornecedor-empresa.component.css'
})
export class ListaFornecedorEmpresaComponent implements OnInit {

  public colunasTabelaFornecedoresPf = ['id', 'cep', 'cpf', 'rg', 'dataNascimento', 'nome', 'email', 'acoes'];
  public colunasTabelaFornecedoresPJ = ['id', 'cep', 'cnpj', 'nomeFantasia', 'email', 'acoes'];
  public fornecedoresPf = new MatTableDataSource<FornecedorPf[]>();
  public fornecedoresPj = new MatTableDataSource<FornecedorPj[]>();
  public empresa: Empresa = new Empresa();
  public totalItems = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private route: ActivatedRoute,
              private empresaService: EmpresaService,
              private fornecedorPjService: FornecedorPjService,
              private fornecedorPfService: FornecedorPfService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.buscarFornecedoresPjPorEmpresa(params['id']);
      this.buscarFornecedoresPfPorEmpresa(params['id']);
      this.buscarEmpresaPorId(params['id']);
    });
  }

  public abrirDialog(): void {
    this.dialog.open(SeletorFornecedorComponent, {data: this.empresa}).afterClosed().subscribe(() => {
      this.buscarFornecedoresPfPorEmpresa(this.empresa.id);
      this.buscarFornecedoresPjPorEmpresa(this.empresa.id);
    });
  }

  public ngAfterViewInit(): void {
    this.fornecedoresPf.sort = this.sort;
    this.fornecedoresPf.paginator = this.paginator;
  }

  public atualizarListaFornecedoresPf(fornecedorPf: FornecedorPf) {

    const snackBar = this.snackBar.open('Confirma que deseja remover o fornecedor?', 'Confirmar', {
      duration: 5000,
    });

    snackBar.onAction().subscribe(() => {
      const listaFornecedores: number[] = this.empresa.fornecedoresPF.filter(f => f.id !== fornecedorPf.id).map(f => f.id);
      this.empresaService.atualizarListaFornecedoresPf(listaFornecedores, this.empresa).subscribe(() => this.buscarFornecedoresPfPorEmpresa(this.empresa.id));
    });
  }

  public atualizarListaFornecedoresPj(fornecedorPj: FornecedorPj) {

    const snackBar = this.snackBar.open('Confirma que deseja remover o fornecedor?', 'Confirmar', {
      duration: 5000,
    });

    snackBar.onAction().subscribe(() => {
      const listaFornecedores: number[] = this.empresa.fornecedoresPJ.filter(f => f.id !== fornecedorPj.id).map(f => f.id);
      this.empresaService.atualizarListaFornecedoresPj(listaFornecedores, this.empresa).subscribe(() => this.buscarFornecedoresPjPorEmpresa(this.empresa.id));
    });
  }

  protected buscarFornecedoresPfPorEmpresa(idEmpresa: number): void {
    this.fornecedorPfService.buscarFornecedoresPfPorEmpresa(idEmpresa).subscribe(
      (dados) => {
        this.fornecedoresPf.data = dados.content;
        this.totalItems = dados.page.totalElements;
        this.paginator.pageIndex = dados.page.number;
        this.paginator.length = dados.page.totalElements;
      },
      (erro) => {
        console.error(erro);
      }
    );
  }

  protected buscarFornecedoresPjPorEmpresa(idEmpresa: number): void {
    this.fornecedorPjService.buscarFornecedoresPjPorEmpresa(idEmpresa).subscribe(
      (dados) => {
        this.fornecedoresPj.data = dados.content;
        this.totalItems = dados.page.totalElements;
        this.paginator.pageIndex = dados.page.number;
        this.paginator.length = dados.page.totalElements;
      },
      (erro) => {
        console.error(erro);
      }
    );
  }

  private buscarEmpresaPorId(idEmpresa: number) {
    this.empresaService.buscarEmpresaPorId(idEmpresa).subscribe(
      (dados) => {
        this.empresa = dados;
      });
  }
}
