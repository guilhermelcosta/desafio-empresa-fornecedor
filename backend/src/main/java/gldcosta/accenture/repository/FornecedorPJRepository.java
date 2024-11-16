package gldcosta.accenture.repository;

import gldcosta.accenture.entity.FornecedorPJ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorPJRepository extends JpaRepository<FornecedorPJ, Long> {

    Page<FornecedorPJ> findAllByEmpresasId(Long empresaId, Pageable pageable);

    Page<FornecedorPJ> findAllByCnpj(String cpf, Pageable pageable);
}
