package gldcosta.accenture.service;

import gldcosta.accenture.entity.Empresa;

import java.util.List;

public interface EmpresaService extends CrudService<Empresa, Empresa, Long> {

//    Page<FornecedorPJ> buscarFornecedoresPorEmpresa(Long idEmpresa, int pagina, int tamanho);

    Empresa atualizarFornecedorPJ(Long idEmpresa, List<Long> idFornecedoresPJ);

    Empresa atualizarFornecedorPF(Long idEmpresa, List<Long> idFornecedoresPF);
}
