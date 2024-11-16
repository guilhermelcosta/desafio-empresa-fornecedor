package gldcosta.accenture.service;

import gldcosta.accenture.entity.FornecedorPF;
import org.springframework.data.domain.Page;

public interface FornecedorPFService extends CrudService<FornecedorPF, FornecedorPF, Long> {

    Page<FornecedorPF> buscarPorEmpresaId(Long empresaId, int pagina, int tamanho);

    Page<FornecedorPF> buscarPorCPF(String cpf, int pagina, int tamanho);
}
