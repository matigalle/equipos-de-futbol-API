package equiposdefutbolapi.exception;

import equiposdefutbolapi.dto.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException() {
        int status = HttpStatus.UNAUTHORIZED.value();

        return ResponseEntity
                .status(status)
                .body(ErrorResponse.builder()
                              .mensaje("Autenticación fallida")
                              .codigo(status)
                              .build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException() {
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.builder()
                              .mensaje(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                              .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                              .build());
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException e) {
        int status = e.getStatusCode().value();

        return ResponseEntity
                .status(status)
                .body(ErrorResponse.builder()
                              .mensaje(e.getStatusText())
                              .codigo(status)
                              .build());
    }

    @ExceptionHandler(value = {
            BadRequestException.class,
            MethodArgumentTypeMismatchException.class,
            MissingRequestValueException.class,
            HttpMessageNotReadableException.class,
            DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException() {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                              .mensaje("La solicitud es inválida")
                              .codigo(HttpStatus.BAD_REQUEST.value())
                              .build());
    }

}
