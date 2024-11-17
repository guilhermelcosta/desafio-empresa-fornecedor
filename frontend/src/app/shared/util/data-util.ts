export function converteDataYYYYMMDD(date: Date): string {

  const ano = date.getFullYear();
  const mes = ('0' + (date.getMonth() + 1)).slice(-2);
  const dia = ('0' + date.getDate()).slice(-2);

  return `${ano}-${mes}-${dia}`;
}
