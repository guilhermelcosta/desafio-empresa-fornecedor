package gldcosta.accenture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gldcosta.accenture.entity.base.PessoaJuridica;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresa")
public class Empresa extends PessoaJuridica {

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "empresa_fornecedor_pf",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private Set<FornecedorPF> fornecedoresPF;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "empresa_fornecedor_pj",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private Set<FornecedorPJ> fornecedoresPJ;
}
