package gldcosta.accenture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gldcosta.accenture.entity.base.PessoaJuridica;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "empresa")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Empresa extends PessoaJuridica {

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "empresa_fornecedor_pessoa_fisica",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private Set<FornecedorPessoaFisica> fornecedoresPessoaFisica;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "empresa_fornecedor_pessoa_juridica",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private Set<FornecedorPessoaJuridica> fornecedoresPessoaJuridica;
}
