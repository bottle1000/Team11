package team11.team11project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team11.team11project.common.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Optional<Member> findMemberByEmail(String email);
}
