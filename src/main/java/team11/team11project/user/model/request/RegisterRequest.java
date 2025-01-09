package team11.team11project.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterRequest {

    private String memberName;
    private String email;
    private String password;
    private String role;
}
