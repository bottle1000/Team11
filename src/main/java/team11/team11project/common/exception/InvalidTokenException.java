package team11.team11project.common.exception;

/**
 * 잘못된 토큰이거나 만료된 토큰인 경우
 */
public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String message) {
        super(message);
    }
}
