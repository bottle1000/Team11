package team11.team11project.common.enums;

import java.util.Arrays;

public enum UserRole {
    OWNER, CUSTOMER;

    /**
     * 유저한테 입력 받은 role은 request에서 String 타입으로 받았기 때문에 이를 Enum타입으로 변환 시켜줄 메서드가 필요
     * @param role : 유저한테 입력 받은 role
     * @return
     */
    public static UserRole fromString(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 권한"));
    }
}
