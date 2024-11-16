import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {EmpresaFornecedorComponent} from './pages/empresa-fornecedor/empresa-fornecedor.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, EmpresaFornecedorComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
