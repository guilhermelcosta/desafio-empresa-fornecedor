package gldcosta.accenture.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadeBase implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @Column
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;
}
