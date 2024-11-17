import {Component, OnInit, ViewChild} from '@angular/core';
import {EmpresaService} from '../../services/empresa.service';
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
import {Empresa} from '../../model/empresa';
import {MatDialog} from '@angular/material/dialog';
import {EmpresaFormComponent} from '../../forms/empresa-form/empresa-form.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {CepPipe} from '../../pipes/cep.pipe';
import {CnpjPipe} from '../../pipes/cnpj.pipe';

@Component({
  selector: 'app-empresa',
  standalone: true,
  imports: [
    MatHeaderRowDef,
    MatHeaderRow,
    MatRowDef,
    MatRow,
    MatButton,
    MatTable,
    MatPaginator,
    MatHeaderCellDef,
    MatColumnDef,
    MatCellDef,
    MatHeaderCell,
    MatCell,
    MatIcon,
    MatIconButton,
    MatSort,
    MatSortModule,
    CepPipe,
    CnpjPipe,
  ],
  templateUrl: './empresa.component.html',
  styleUrl: './empresa.component.css'
})
export class EmpresaComponent implements OnInit {

  public colunasTabela = ['id', 'cep', 'cnpj', 'nomeFantasia', 'email', 'acoes'];
  public dados = new MatTableDataSource<Empresa>();
  public totalItems = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private empresaService: EmpresaService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  public ngOnInit(): void {
    this.buscarEmpresas();
  }

  public ngAfterViewInit(): void {
    this.dados.sort = this.sort;
    this.dados.paginator = this.paginator;
  }

  public abrirDialog(empresa?: Empresa): void {
    this.dialog.open(EmpresaFormComponent, {data: empresa || new Empresa()}).afterClosed().subscribe(() => this.buscarEmpresas());
  }

  public deletarEmpresa(empresa: Empresa) {
    const snackBar = this.snackBar.open('Confirma que deseja deletar a empresa?', 'Confirmar', {
      duration: 5000,
    });

    snackBar.onAction().subscribe(() => {
      this.empresaService.deletarEmpresa(empresa).subscribe(() => this.buscarEmpresas());
    });
  }

  protected buscarEmpresas(indice?: number, tamanho?: number): void {
    this.empresaService.buscarEmpresas(indice, tamanho).subscribe(
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
