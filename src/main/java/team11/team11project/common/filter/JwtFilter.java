package team11.team11project.common.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team11.team11project.common.exception.InvalidTokenException;
import team11.team11project.common.exception.NotFoundAuthException;
import team11.team11project.common.utils.JwtUtil;

import java.io.IOException;


@Slf4j(topic = "JwtFilter")
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String jwt = null;

        /**
         * 로그인 URI로 들어오면 통과
         */
        if (requestURI.equals("/api/members/login") || requestURI.equals("/api/members/register")) {
            filterChain.doFilter(request, response);
            return;
        }


        /**
         * 로그인 성공했다 가정하면 아래부터
         */
        String authorizationHeader = request.getHeader("Authorization");

        /**
         * 요청 헤더에 Authorization 이 없거나 또는, Authorization 가 Bearer 로 시작하지 않는다면 예외처리
         */
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotFoundAuthException("JWT 토큰이 필요합니다");
        }

        /**
         * authorizationHeader는 Bearer + secretKey이기 때문에 순수 secretKey를 얻기 위한 작업
         */
        jwt = authorizationHeader.substring(7);

        /**
         * JwtUtil 클래스에 만든 토큰 검증 로직으로 만약 jwt가 유효하지 않다면 상태코드 forbidden
         */
        if (!jwtUtil.validateToken(jwt)) {
            throw new InvalidTokenException("Token이 유효하지 않습니다.");
        }


    }
}
