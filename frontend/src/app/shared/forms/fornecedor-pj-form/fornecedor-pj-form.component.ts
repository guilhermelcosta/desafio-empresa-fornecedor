import {Component, Inject} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {NgIf} from "@angular/common";
import {NgxMaskDirective, provideNgxMask} from "ngx-mask";
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {catchError, of} from 'rxjs';
import {MatTooltip} from '@angular/material/tooltip';
import {FornecedorPj} from '../../model/fornecedor-pj';
import {FornecedorPjService} from '../../services/fornecedor-pj.service';
import {MatSelectModule} from '@angular/material/select';
import {MatOptionModule} from '@angular/material/core';

@Component({
  selector: 'app-fornecedor-pj-form',
  standalone: true,
  imports: [
   MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButton,
    FormsModule,
    NgxMaskDirective,
    NgIf,
    MatIcon,
    MatTooltip,
  ],
  providers: [provideNgxMask()],
  templateUrl: './fornecedor-pj-form.component.html',
  styleUrl: './fornecedor-pj-form.component.css'
})
export class FornecedorPjFormComponent {

  private readonly endpointCriar!: boolean;

  constructor(private dialogRef: MatDialogRef<FornecedorPjFormComponent>,
              private fornecedorPjService: FornecedorPjService,
              private snackBar: MatSnackBar,
              @Inject(MAT_DIALOG_DATA) public fornecedorPJ: FornecedorPj) {

    this.endpointCriar = this.fornecedorPJ.cnpj === '';
  }

  public aoCancelar(): void {
    this.dialogRef.close();
  }

  public aoConfirmar(): void {
    this.endpointCriar
      ? this.criarFornecedorPj()
      : this.atualizarFornecedorPj();
  }

  private criarFornecedorPj() {
    this.fornecedorPjService.criarFornecedorPj(this.fornecedorPJ).pipe(
      catchError(e => {
        this.snackBar.open(`${e.error.mensagem}`, 'Fechar', {
          duration: 5000,
        });
        return of(null);
      })
    ).subscribe(result => {
      if (result)
        this.dialogRef.close();
    });
  }

  private atualizarFornecedorPj() {
    this.fornecedorPjService.atualizarFornecedorPj(this.fornecedorPJ).pipe(
      catchError(e => {
        this.snackBar.open(`${e.error.mensagem}`, 'Fechar', {
          duration: 5000,
        });
        return of(null);
      })
    ).subscribe(result => {
      if (result)
        this.dialogRef.close();
    });
  }
}
