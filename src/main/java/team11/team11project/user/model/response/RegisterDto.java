package team11.team11project.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team11.team11project.common.entity.Member;

@AllArgsConstructor
@Getter
public class RegisterDto {

    private String message;
    private Long id;

    public static RegisterDto convertDto(Member member) {
        return new RegisterDto(
                member.getEmail(),
                member.getId()
        );
    }
}
