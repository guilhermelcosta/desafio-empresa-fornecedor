import {Component, OnInit, ViewChild} from '@angular/core';
import {MatButton, MatIconButton} from "@angular/material/button";
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
} from "@angular/material/table";
import {MatIcon} from "@angular/material/icon";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort, MatSortHeader} from "@angular/material/sort";
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {FornecedorPjService} from '../../services/fornecedor-pj.service';
import {FornecedorPjFormComponent} from '../../forms/fornecedor-pj-form/fornecedor-pj-form.component';
import {FornecedorPj} from '../../model/fornecedor-pj';
import {CnpjPipe} from '../../pipes/cnpj.pipe';
import {CepPipe} from '../../pipes/cep.pipe';

@Component({
  selector: 'app-fornecedor-pj',
  standalone: true,
  imports: [
    MatButton,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatIcon,
    MatIconButton,
    MatPaginator,
    MatRow,
    MatRowDef,
    MatSort,
    MatSortHeader,
    MatTable,
    MatHeaderCellDef,
    CnpjPipe,
    CepPipe
  ],
  templateUrl: './fornecedor-pj.component.html',
  styleUrl: './fornecedor-pj.component.css'
})
export class FornecedorPjComponent implements OnInit {

  public colunasTabela = ['id', 'cep', 'cnpj', 'nomeFantasia', 'email', 'acoes'];
  public dados = new MatTableDataSource<FornecedorPj>();
  public totalItems = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private fornecedorPjService: FornecedorPjService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  public ngOnInit(): void {
    this.buscarFornecedoresPj();
  }

  public ngAfterViewInit(): void {
    this.dados.sort = this.sort;
    this.dados.paginator = this.paginator;
  }

  public abrirDialog(fornecedorPj?: FornecedorPj): void {
    this.dialog.open(FornecedorPjFormComponent, {data: fornecedorPj || new FornecedorPj()}).afterClosed().subscribe(() => this.buscarFornecedoresPj());
  }

  public deletarEmpresa(fornecedorPj: FornecedorPj) {
    const snackBar = this.snackBar.open('Confirma que deseja deletar o fornecedor?', 'Confirmar', {
      duration: 5000,
    });

    snackBar.onAction().subscribe(() => {
      this.fornecedorPjService.deletarFornecedorPj(fornecedorPj).subscribe(() => this.buscarFornecedoresPj());
    });
  }

  protected buscarFornecedoresPj(indice?: number, tamanho?: number): void {
    this.fornecedorPjService.buscarFornecedoresPj(indice, tamanho).subscribe(
      (data: any) => {
        this.dados.data = data.content;
        this.totalItems = data.page.totalElements;
        this.paginator.pageIndex = data.page.number;
        this.paginator.length = data.page.totalElements;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

}
