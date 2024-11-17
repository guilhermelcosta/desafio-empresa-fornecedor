import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'dataDdmmyyyy',
  standalone: true
})
export class DataDdmmyyyyPipe implements PipeTransform {

  transform(valor: string): string {
    if (!valor) return valor;
    const [ano, mes, dia] = valor.split('-');
    return `${dia}/${mes}/${ano}`;
  }

}
