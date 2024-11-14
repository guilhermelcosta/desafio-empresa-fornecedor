package gldcosta.accenture.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record FornecedorPessoaJuridicaDTO(String cep,
                                          @Pattern(message = "formato esperado: xxx@xxx.com.br ou xxx@xxx.com", regexp = "^[A-Za-z0-9.]+@[A-Za-z0-9]+\\.[A-Za-z]+\\.?([A-Za-z]+)?$")
                                          String email,
                                          String cnpj,
                                          String nomeFantasia,
                                          Set<Long> empresaIds) implements Serializable {
}