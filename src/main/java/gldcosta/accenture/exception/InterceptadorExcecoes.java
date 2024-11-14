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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@RestControllerAdvice
public class InterceptadorExcecoes extends DefaultHandlerExceptionResolver {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> capturarEntityNotFoundException(EntityNotFoundException e) {

        String mensagemErro = e.getMessage();

        return construirMensagemErro(mensagemErro, NOT_FOUND);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class, ConstraintViolationException.class})
    public ResponseEntity<Object> capturarBadRequest(Exception e) {

        String msgErro = e.getMessage();

        return construirMensagemErro(msgErro, BAD_REQUEST);
    }

    private ResponseEntity<Object> construirMensagemErro(String message,
                                                         HttpStatus httpStatus) {

        RespostaErro respostaErro = new RespostaErro(httpStatus.value(), message);

        return ResponseEntity.status(httpStatus).body(respostaErro);
    }
}
