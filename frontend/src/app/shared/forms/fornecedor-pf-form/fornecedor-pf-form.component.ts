import {Component, Inject} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatError, MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {NgIf} from "@angular/common";
import {NgxMaskDirective, provideNgxMask} from "ngx-mask";
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {catchError, of} from 'rxjs';
import {MatTooltip} from '@angular/material/tooltip';
import {FornecedorPf} from '../../model/fornecedor-pf';
import {FornecedorPfService} from '../../services/fornecedor-pf.service';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';

@Component({
  selector: 'app-fornecedor-pf-form',
  standalone: true,
  imports: [
    FormsModule,
    MatButton,
    MatError,
    MatFormField,
    MatIcon,
    MatInput,
    MatLabel,
    MatSuffix,
    NgIf,
    NgxMaskDirective,
    ReactiveFormsModule,
    MatTooltip,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatDatepicker
  ],
  providers: [provideNgxMask(), provideNativeDateAdapter()],
  templateUrl: './fornecedor-pf-form.component.html',
  styleUrl: './fornecedor-pf-form.component.css'
})
export class FornecedorPfFormComponent {

  private readonly endpointCriar!: boolean;

  constructor(private dialogRef: MatDialogRef<FornecedorPfFormComponent>,
              private fornecedorPfService: FornecedorPfService,
              private snackBar: MatSnackBar,
              @Inject(MAT_DIALOG_DATA) public fornecedorPf: FornecedorPf) {

    this.endpointCriar = this.fornecedorPf.cpf === '';
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
    this.fornecedorPfService.criarFornecedorPf(this.fornecedorPf).pipe(
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
    this.fornecedorPfService.atualizarFornecedorPf(this.fornecedorPf).pipe(
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
