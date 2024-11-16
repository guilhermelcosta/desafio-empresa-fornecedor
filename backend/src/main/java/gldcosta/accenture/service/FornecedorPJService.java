package gldcosta.accenture.service;

import gldcosta.accenture.entity.FornecedorPJ;
import org.springframework.data.domain.Page;

public interface FornecedorPJService extends CrudService<FornecedorPJ, FornecedorPJ, Long> {

    Page<FornecedorPJ> buscarPorEmpresaId(Long empresaId, int pagina, int tamanho);

    Page<FornecedorPJ> buscarPorCNPJ(String cpf, int pagina, int tamanho);
}
