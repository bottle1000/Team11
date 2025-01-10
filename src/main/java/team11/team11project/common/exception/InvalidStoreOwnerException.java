package team11.team11project.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidStoreOwnerException extends ResponseStatusException {
    public InvalidStoreOwnerException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
