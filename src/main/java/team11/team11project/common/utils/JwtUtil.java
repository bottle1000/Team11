package team11.team11project.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team11.team11project.common.enums.UserRole;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

    public static final String BEARER_PREFIX = "Bearer ";
    private final long TOKEN_TIME = 60 * 60 * 1000L;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    @Value("asjdkjfxcjvxbjfsdrjhweuhrkalsdjjczxjhkjfhsadsjklas")
    private String secretKey;
    private Key key;

    /**
     * 초기 설정
     */
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    /**
     * 정보 추출
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 토큰 생성
     * @param memberId
     * @param memberName
     * @param userRole
     * @return
     */
    public String generateToken(Long memberId, String memberName, UserRole userRole) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(String.valueOf(memberId))
                        .claim("memberName", memberName)
                        .claim("auth", userRole)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    public String extractRoles(String token) {
        return extractAllClaims(token).get("auth", String.class);
    }

    public boolean hasRole(String token, String role) {
        String roles = extractRoles(token);
        return roles.contains(role);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).get("memberName", String.class);
    }

    public Long extractUserId(String token) {
        return Long.parseLong(extractAllClaims(token).getSubject());
    }

    /**
     * 토큰 검증
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            // JWT 파서 빌더를 사용하여 토큰의 서명을 검증합니다.
            // setSigningKey() 메서드를 사용하여 서명 검증에 사용할 비밀 키를 설정합니다.
            // parseClaimsJws() 메서드를 사용하여 토큰의 클레임을 파싱하고 서명을 검증합니다.
            Jwts.parserBuilder()
                    .setSigningKey(key) // 비밀 키 설정
                    .build() // 파서 빌더 빌드
                    .parseClaimsJws(token); // 토큰 파싱 및 검증
            return true; // 토큰이 유효한 경우
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            // 토큰 서명이 잘못되었거나, 잘못된 형식의 JWT가 전달된 경우
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.", e);
        } catch (ExpiredJwtException e) {
            // 토큰이 만료된 경우
            log.error("Expired JWT token, 만료된 JWT token 입니다.", e);
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 JWT 형식이 전달된 경우
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.", e);
        } catch (IllegalArgumentException e) {
            // JWT 클레임이 비어 있거나 잘못된 형식일 경우
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.", e);
        }
        return false; // 예외가 발생한 경우 토큰이 유효하지 않음
    }
}
