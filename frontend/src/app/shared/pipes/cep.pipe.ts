import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'cep',
  standalone: true
})
export class CepPipe implements PipeTransform {

  transform(valor: string): string {
    if (!valor) return valor;
    return valor.replace(/^(\d{5})(\d{3})$/, '$1-$2');
  }

}
