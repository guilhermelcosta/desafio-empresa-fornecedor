import {Component, ViewChild} from '@angular/core';
import {CepPipe} from "../../pipes/cep.pipe";
import {MatButton, MatButtonModule, MatIconButton} from "@angular/material/button";
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
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort, MatSortHeader} from "@angular/material/sort";
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {FornecedorPf} from '../../model/fornecedor-pf';
import {FornecedorPfService} from '../../services/fornecedor-pf.service';
import {CpfPipe} from '../../pipes/cpf.pipe';
import {FornecedorPfFormComponent} from '../../forms/fornecedor-pf-form/fornecedor-pf-form.component';
import {DataDdmmyyyyPipe} from '../../pipes/data-ddmmyyyy.pipe';
import {MatTooltip} from '@angular/material/tooltip';
import {MatFormField, MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-fornecedor-pf',
  standalone: true,
  imports: [
    CepPipe,
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
    CpfPipe,
    DataDdmmyyyyPipe,
    MatTooltip,
    MatFormField,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    NgIf
  ],
  templateUrl: './fornecedor-pf.component.html',
  styleUrl: './fornecedor-pf.component.css'
})
export class FornecedorPfComponent {

  public colunasTabela = ['id', 'cep', 'cpf', 'rg', 'dataNascimento', 'nome', 'email', 'acoes'];
  public dados = new MatTableDataSource<FornecedorPf>();
  public totalItems = 0;
  public cpfDigitado = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private fornecedorPfService: FornecedorPfService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  public ngOnInit(): void {
    this.buscarFornecedoresPf();
  }

  public ngAfterViewInit(): void {
    this.dados.sort = this.sort;
    this.dados.paginator = this.paginator;
  }

  public abrirDialog(fornecedorPf?: FornecedorPf): void {
    this.dialog.open(FornecedorPfFormComponent, {data: fornecedorPf || new FornecedorPf()}).afterClosed().subscribe(() => this.buscarFornecedoresPf());
  }

  public deletarEmpresa(fornecedorPf: FornecedorPf) {
    const snackBar = this.snackBar.open('Confirma que deseja deletar o fornecedor?', 'Confirmar', {
      duration: 5000,
    });

    snackBar.onAction().subscribe(() => {
      this.fornecedorPfService.deletarFornecedorPf(fornecedorPf).subscribe(() => this.buscarFornecedoresPf());
    });
  }

  public calcularIdade(dataNascimento: string): number {

    const hoje = new Date();
    const nascimento = new Date(dataNascimento);
    const mes = hoje.getMonth() - nascimento.getMonth();
    let idade = hoje.getFullYear() - nascimento.getFullYear();

    if (mes < 0 || (mes === 0 && hoje.getDate() < nascimento.getDate()))
      idade--;

    return idade;
  }

  public buscarFornecedoresPorCpf(cpfDigitado: string) {
    const cpfLimpo = this.limparCpf(cpfDigitado);
    this.fornecedorPfService.buscarFornecedoresPorCpf(cpfLimpo).subscribe(
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

  protected buscarFornecedoresPf(indice?: number, tamanho?: number): void {
    this.fornecedorPfService.buscarFornecedoresPf(indice, tamanho).subscribe(
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

  private limparCpf(cpf: string): string {
    return cpf.replace(/[\s.-]/g, '');
  }
}
