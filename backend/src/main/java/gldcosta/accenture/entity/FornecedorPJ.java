package gldcosta.accenture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@Table(name = "fornecedor_pj")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FornecedorPJ extends PessoaJuridica {

    @JsonIgnore
    @ManyToMany(mappedBy = "fornecedoresPJ")
    private Set<Empresa> empresas;
}
