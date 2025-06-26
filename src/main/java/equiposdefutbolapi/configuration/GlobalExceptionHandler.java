package equiposdefutbolapi.configuration;

import equiposdefutbolapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

}
