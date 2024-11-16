package gldcosta.accenture.repository;

import gldcosta.accenture.entity.Empresa;
import gldcosta.accenture.entity.FornecedorPJ;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
