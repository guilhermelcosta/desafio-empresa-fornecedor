package gldcosta.accenture.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import static org.springframework.http.HttpStatus.*;

@Component
@RestControllerAdvice
public class InterceptadorExcecoes extends DefaultHandlerExceptionResolver {

    /**
     * Intercepta exceções de entidade não encontrada
     *
     * @param e Exceção de entidade não encontrada
     * @return Resposta de erro
     */
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> capturarEntityNotFoundException(EntityNotFoundException e) {

        String mensagemErro = e.getMessage();

        return construirMensagemErro(mensagemErro, NOT_FOUND);
    }

    /**
     * Intercepta exceções de bad request
     *
     * @param e Exceção de bad request
     * @return Resposta de erro
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class, ConstraintViolationException.class})
    public ResponseEntity<Object> capturarBadRequest(Exception e) {

        String msgErro = e.getMessage();

        return construirMensagemErro(msgErro, BAD_REQUEST);
    }

    /**
     * Intercepta exceções de argumento não válido
     *
     * @param e Exceção de argumento não válido
     * @return Resposta de erro
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> capturarMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String mensagemErro = "Argumento inválido: " + e.getBindingResult().getFieldError().getDefaultMessage();

        return construirMensagemErro(mensagemErro, BAD_REQUEST);
    }

    /**
     * Intercepta exceções de argumento ilegal
     *
     * @param e Exceção de argumento ilegal
     * @return Resposta de erro
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> capturarIllegalArgumentException(IllegalArgumentException e) {

        String mensagemErro = e.getMessage();

        return construirMensagemErro(mensagemErro, BAD_REQUEST);
    }

    /**
     * Intercepta exceções de chave duplicada no banco de dados
     *
     * @param e Exceção de chave duplicada
     * @return Resposta de erro
     */
    @ResponseStatus(CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> capturarPSQLException(DataIntegrityViolationException e) {

        String mensagemErro = "Erro de chave duplicada: CPF/CNPJ/RG já cadastrado";

        return construirMensagemErro(mensagemErro, CONFLICT);
    }


    /**
     * Constrói a mensagem de erro
     *
     * @param message    Mensagem de erro
     * @param httpStatus Status HTTP
     * @return Resposta de erro
     */
    private ResponseEntity<Object> construirMensagemErro(String message,
                                                         HttpStatus httpStatus) {

        RespostaErro respostaErro = new RespostaErro(httpStatus.value(), message);

        return ResponseEntity.status(httpStatus).body(respostaErro);
    }
}
