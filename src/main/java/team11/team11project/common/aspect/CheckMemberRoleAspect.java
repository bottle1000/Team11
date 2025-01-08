package team11.team11project.common.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Aspect
public class CheckMemberRoleAspect {

    private final HttpServletRequest request;

    @Pointcut("execution(* team11.team11project.store.service.StoreService.*(..))")
    public void allControllerMethods() {
    }

    @Before("allControllerMethods()")
    public void accessBefore() {
        log.info("aspect 클래스 진입");
        String requestURI = request.getRequestURI();

        //요청에서 역할 가져오기
        String role = (String) request.getAttribute("role");
        log.info(role);
        if (role == null) {
            throw new IllegalStateException("사용자 권한 정보가 없습니다.");
        }

        if (requestURI.startsWith("/api/stores")) {
            if (!"OWNER".equals(role)) {
                throw new IllegalStateException("사장님 권한만 접근할 수 있습니다.");
            }
        }
    }
}
