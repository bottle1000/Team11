package team11.team11project.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import team11.team11project.common.enums.UserRole;
import team11.team11project.user.model.request.RegisterRequest;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE member SET is_deleted = true WHERE member_id = ?")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String memberName;
    private String email;
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    private boolean is_deleted = Boolean.FALSE;

    /**
     * 유저 생성을 엔티티에 위임
     * 정적 팩토리 메서드 사용
     */
    public static Member createMember(RegisterRequest request, String encodedPassword) {
        return new Member(
                null,
                request.getMemberName(),
                request.getEmail(),
                encodedPassword,
                UserRole.fromString(request.getRole()),
                false
        );
    }

}
