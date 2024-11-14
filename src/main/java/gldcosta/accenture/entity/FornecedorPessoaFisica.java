package gldcosta.accenture.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gldcosta.accenture.entity.base.Pessoa;
import gldcosta.accenture.entity.base.PessoaFisica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fornecedor_pessoa_fisica")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FornecedorPessoaFisica extends PessoaFisica {

    @ManyToMany(mappedBy = "fornecedoresPessoaFisica")
    private Set<Empresa> empresas;
}
