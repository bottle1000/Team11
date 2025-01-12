package team11.team11project.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MissingRequiredFieldException extends ResponseStatusException {
    public MissingRequiredFieldException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
