package gldcosta.accenture.entity.base;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Pessoa extends EntidadeBase {

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z0-9.]+@[A-Za-z0-9]+\\.[A-Za-z]+\\.?([A-Za-z]+)?$", message = "formato esperado: xxx@xxx.com.br ou xxx@xxx.com")
    private String email;
}
