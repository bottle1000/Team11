package team11.team11project.common.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import team11.team11project.common.exception.UnauthorizedAccessException;
import team11.team11project.common.exception.UserRoleNotFoundException;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
@Aspect
public class CheckRoleStoreAspect {

    private final HttpServletRequest request;

    /**
     * OWNER 또는 CUSTOMER 전용 권한 AOP
     */
    @Around("@annotation(team11.team11project.common.aspect.AuthCheck)")
    public Object checkRole(ProceedingJoinPoint joinPoint) throws Throwable{

        //권한 가져오기
        String role = (String) request.getAttribute("role");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        AuthCheck authCheck = method.getAnnotation(AuthCheck.class);

        //권한 정보가 없는 경우 예외 처리
        if (role == null) {
            throw new UserRoleNotFoundException("사용자 권한 정보가 없습니다.");
        }

        //애노테이션 권한 값과 요청 받은 권한 값을 확인. 만약 다르면 예외처리.
        if (Arrays.stream(authCheck.value()).noneMatch(role::equals)) {
            throw new UnauthorizedAccessException(String.format(" %s 권한만 접근할 수 있습니다.",
                    Arrays.toString(authCheck.value())));
        }

        log.info("권한 확인 완료. 요청 권한 : {}, 필요 권한 : {}", role, authCheck.value() );
        return joinPoint.proceed();
    }
}
