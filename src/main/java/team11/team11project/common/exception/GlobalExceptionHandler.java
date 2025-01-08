package team11.team11project.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundAuthException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundAuthException(NotFoundAuthException e) {
        log.error("오류 메시지 : {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidTokenException(InvalidTokenException e) {
        log.error("오류 메시지 : {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateEmailException(DuplicateEmailException e) {
        log.error("오류 메시지 : {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handlerResponseStatusException(NotFoundException e) {

        log.error("오류 메시지: {}", e.getMessage());

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), e.getStatusCode().value());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
