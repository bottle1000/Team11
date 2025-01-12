package team11.team11project.user.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team11.team11project.common.encode.PasswordEncoder;
import team11.team11project.common.entity.Member;
import team11.team11project.common.enums.UserRole;
import team11.team11project.common.exception.DuplicateEmailException;
import team11.team11project.common.exception.IncorrectPasswordException;
import team11.team11project.common.exception.NotFoundEmailException;
import team11.team11project.user.model.request.LoginRequest;
import team11.team11project.user.model.request.RegisterRequest;
import team11.team11project.user.model.response.LoginDto;
import team11.team11project.user.model.response.RegisterDto;
import team11.team11project.user.repository.MemberRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeAll
    void 초기_셋팅() {
        memberRepository.deleteAll(); // 데이터 삽입 중복을 피하기 위해 DB에 있는 데이터들 정리
        String encodedPassword = passwordEncoder.encode("Password1234!"); // 데이터에 저장할 때는 암호화가 적용되기 때문에
        memberRepository.save(Member.createMember(
                new RegisterRequest(
                        "유저테스트",
                        "usertest@example.com",
                        "Password1234!",
                        "CUSTOMER"),
                        encodedPassword
        ));
    }

    @Test
    @DisplayName("회원 가입 테스트 - 성공")
    void 회원가입_테스트_성공() {
        //given
        RegisterRequest requestMember = new RegisterRequest(
                "박병천", "example@example.com", "Qudcjs1234!", "CUSTOMER");
        //when
        RegisterDto result = memberService.registerMember(requestMember);

        //then
        assertEquals("박병천", result.getMemberName());
        assertEquals("example@example.com", result.getEmail());
        assertEquals(UserRole.CUSTOMER, result.getUserRole());
    }

    @Test
    @DisplayName("회원가입 테스트 - 실패(중복 이메일)")
    void 회원가입_중_중복된_이메일로_예외발생() {
        //given
        RegisterRequest requestMember = new RegisterRequest(
                "중복 유저",
                "usertest@example.com",
                "Password1234!",
                "CUSTOMER"
        );

        //when
        //then
        Assertions.assertThrows(DuplicateEmailException.class, () ->
                memberService.registerMember(requestMember));
    }

    @Test
    @DisplayName("로그인 테스트 - 성공")
    void 로그인_테스트_성공() {
        //given
        LoginRequest loginRequest = new LoginRequest(
                "usertest@example.com",
                "Password1234!"
        );

        //when
        LoginDto loginDto = memberService.LoginMember(loginRequest);

        //then
        assertNotNull(loginDto.getToken());
    }

    @Test
    @DisplayName("로그인 테스트 - 실패(비밀번호 불일치)")
    void 로그인_테스트_실패_비밀번호_불일치() {
        //given
        LoginRequest loginRequest = new LoginRequest(
                "usertest@example.com",
                "Password123!"
        );

        //when
        //then
        Assertions.assertThrows(IncorrectPasswordException.class, ()->
                memberService.LoginMember(loginRequest));
    }

    @Test
    @DisplayName("로그인 테스트 - 실패(존재하지 않는 이메일)")
    void 로그인_테스트_실패_이메일_불일치() {
        // Given
        LoginRequest loginRequest = new LoginRequest(
                "test@example.com",
                "Password1234!"
        );

        // When & Then
        Assertions.assertThrows(NotFoundEmailException.class, () ->
                memberService.LoginMember(loginRequest));
    }

    /**
     * 논리저 삭제가 이루어졌는데 findMember에서 1차 캐시에 있는 상태라.. 어떻게 테스트 코드를 짜야하는지 모르겠음...
     * 테스트 오류 나는 코드
     */
    @Test
    @DisplayName("회원 삭제 테스트")
    void 회원_삭제_테스트() {
        //given
        Optional<Member> findMember = memberRepository.findMemberByEmail("usertest@example.com");
        //when
        memberService.deleteMember(findMember.get().getId(), "Password1234!", null);
        //then
        assertTrue(findMember.get().is_deleted());
    }
}