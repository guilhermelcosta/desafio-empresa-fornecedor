import {Component} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatOptionModule} from '@angular/material/core';
import {MatButton} from '@angular/material/button';
import {MatDialogRef} from '@angular/material/dialog';
import {EmpresaService} from '../../services/empresa.service';
import {FormsModule} from '@angular/forms';
import {Empresa} from '../../model/empresa';
import {NgxMaskDirective, provideNgxMask} from 'ngx-mask';

@Component({
  selector: 'app-empresa-form',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButton,
    FormsModule,
    NgxMaskDirective,
  ],
  providers: [provideNgxMask()],
  templateUrl: './empresa-form.component.html',
  styleUrl: './empresa-form.component.css'
})
export class EmpresaFormComponent {

  public empresa: Empresa = new Empresa();

  constructor(private dialogRef: MatDialogRef<EmpresaFormComponent>,
              private empresaService: EmpresaService) {
  }

  aoCancelar(): void {
    this.dialogRef.close();
  }

  aoConfirmar(): void {
    this.empresaService.criarEmpresa(this.empresa).subscribe(() => {
      this.dialogRef.close();
    });
  }
}
