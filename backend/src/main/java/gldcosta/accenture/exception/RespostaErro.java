package gldcosta.accenture.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Data
public class RespostaErro {

    private final int status;
    private final String mensagem;
    private List<ErroValidacao> errosValidacao;

    /**
     * Adiciona um erro de validação à resposta.
     *
     * @param field   O campo que falhou na validação.
     * @param message A mensagem de erro.
     */
    public void adicionarErroValidacao(String field, String message) {

        if (isNull(errosValidacao))
            this.errosValidacao = new ArrayList<>();

        this.errosValidacao.add(new ErroValidacao(field, message));
    }
}
