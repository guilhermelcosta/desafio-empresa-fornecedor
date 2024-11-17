import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'cep',
  standalone: true
})
export class CepPipe implements PipeTransform {

  transform(value: string): string {
    if (!value) return value;
    return value.replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/, '$1.$2.$3/$4-$5');
  }

}
