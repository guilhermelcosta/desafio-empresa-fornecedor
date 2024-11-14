package gldcosta.accenture.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gldcosta.accenture.entity.base.PessoaFisica;
import gldcosta.accenture.entity.base.PessoaJuridica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fornecedor_pessoa_juridica")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FornecedorPessoaJuridica extends PessoaJuridica {

    @ManyToMany(mappedBy = "fornecedoresPessoaJuridica")
    private Set<Empresa> empresas;
}
