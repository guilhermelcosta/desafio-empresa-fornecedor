package gldcosta.accenture.repository;

import gldcosta.accenture.entity.FornecedorPF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorPFRepository extends JpaRepository<FornecedorPF, Long> {

    Page<FornecedorPF> findAllByEmpresasId(Long empresaId, Pageable pageable);

    Page<FornecedorPF> findAllByCpf(String cpf, Pageable pageable);
}
