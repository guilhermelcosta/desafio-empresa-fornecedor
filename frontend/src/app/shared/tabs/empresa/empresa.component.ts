import {Component, OnInit} from '@angular/core';
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
  MatTable
} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {Empresa} from '../../model/empresa';
import {MatButton} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {EmpresaFormComponent} from '../../forms/empresa-form/empresa-form.component';

@Component({
  selector: 'app-empresa',
  standalone: true,
  imports: [
    MatTable,
    MatColumnDef,
    MatHeaderCell,
    MatCellDef,
    MatCell,
    MatHeaderCellDef,
    MatPaginator,
    MatHeaderRowDef,
    MatHeaderRow,
    MatRowDef,
    MatRow,
    MatButton
  ],
  templateUrl: './empresa.component.html',
  styleUrl: './empresa.component.css'
})
export class EmpresaComponent implements OnInit {

  public colunasTabela = ['id', 'cep', 'cnpj', 'nomeFantasia', 'email'];
  public dados: Empresa[] = [];

  constructor(private empresaService: EmpresaService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.buscarEmpresas();
  }

  public abrirDialog(): void {
    this.dialog.open(EmpresaFormComponent).afterClosed().subscribe(() => this.buscarEmpresas());
  }

  private buscarEmpresas(): void {
    this.empresaService.buscarEmpresas().subscribe(
      (data: any) => {
        this.dados = data.content;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

}
