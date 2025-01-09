package team11.team11project.common.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import team11.team11project.common.exception.UnauthorizedAccessException;
import team11.team11project.common.exception.UserRoleNotFoundException;

@Slf4j
@Component
@RequiredArgsConstructor
@Aspect
public class CheckRoleStoreAspect {

    private final HttpServletRequest request;

    /**
     * 가게 생성, 수정, 폐업은 OWNER만 가능
     */
    @Before("execution(* team11.team11project.store.controller.StoreController.createStore(..))")
//            "execution(* team11.team11project.store.controller.StoreController.updateStore(..)) ||" +
//            "execution(* team11.team11project.store.controller.StoreController.deleteStore(..))" )
    public void checkCreateStoreRole() {
        //요청 URI와 권한 가져오기
        String requestURI = request.getRequestURI();
        String role = (String) request.getAttribute("role");

        //권한 정보가 없는 경우 예외 처리
        if (role == null) {
            throw new UserRoleNotFoundException("사용자 권한 정보가 없습니다.");
        }

        if (requestURI.startsWith("/api/stores")) {
            if (!"OWNER".equals(role)) {
                throw new UnauthorizedAccessException("사장님 권한만 접근할 수 있습니다.");
            }
        }

        log.info("권한 확인 완료. 권한 : {}, 요청 URI : {}", role, requestURI);
    }
}
