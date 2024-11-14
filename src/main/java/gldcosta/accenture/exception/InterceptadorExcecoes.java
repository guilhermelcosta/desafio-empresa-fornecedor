package gldcosta.accenture.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@RestControllerAdvice
public class InterceptadorExcecoes extends DefaultHandlerExceptionResolver {

    /**
     * Captura e trata exceções quando uma entidade não é encontrada no banco de dados.
     * Retorna um erro de status 404 (Not Found).
     *
     * @param e       A exceção capturada.
     * @param request O objeto de requisição.
     * @return Uma resposta HTTP com o status e a mensagem de erro.
     */
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> capturaEntityNotFoundException(EntityNotFoundException e, WebRequest request) {

        String mensagemErro = e.getMessage();

        return buildErrorMessage(e, mensagemErro, NOT_FOUND, request);
    }

    /**
     * Captura e trata exceções relacionadas a argumentos de método inválidos ou leituras incorretas de mensagens HTTP.
     * Retorna um erro de status 400 (Bad Request).
     *
     * @param e       A exceção capturada.
     * @param request O objeto de requisição.
     * @return Uma resposta HTTP com o status e a mensagem de erro.
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class, ConstraintViolationException.class})
    public ResponseEntity<Object> captureBadRequest(Exception e, WebRequest request) {

        String msgErro = e.getMessage();

        return buildErrorMessage(e, msgErro, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Constrói uma mensagem de erro padronizada a partir de uma exceção, status HTTP e a requisição.
     * Inclui o stack trace da exceção se a propriedade `imprimirStackTrace` estiver habilitada.
     *
     * @param e          A exceção capturada.
     * @param message    A mensagem de erro personalizada.
     * @param httpStatus O status HTTP correspondente.
     * @param request    O objeto de requisição.
     * @return Uma resposta HTTP com a mensagem de erro formatada.
     */
    private ResponseEntity<Object> buildErrorMessage(Exception e, String message,
                                                     HttpStatus httpStatus, WebRequest request) {

        RespostaErro respostaErro = new RespostaErro(httpStatus.value(), message);

        return ResponseEntity.status(httpStatus).body(respostaErro);
    }
}
