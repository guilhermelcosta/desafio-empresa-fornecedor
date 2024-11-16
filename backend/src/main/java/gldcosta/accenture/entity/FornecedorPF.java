package gldcosta.accenture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@Table(name = "fornecedor_pf")
public class FornecedorPF extends PessoaFisica {

    @JsonIgnore
    @ManyToMany(mappedBy = "fornecedoresPF")
    private Set<Empresa> empresas;
}
