package team11.team11project.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 예외 공통 응답 객체
 */
@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String message; // 오류 메시지
    private int status; // 응답 코드
}
