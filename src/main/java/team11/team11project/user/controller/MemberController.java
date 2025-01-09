package team11.team11project.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team11.team11project.common.aspect.AuthCheck;
import team11.team11project.user.model.request.LoginRequest;
import team11.team11project.user.model.request.RegisterRequest;
import team11.team11project.user.model.response.LoginDto;
import team11.team11project.user.model.response.RegisterDto;
import team11.team11project.user.service.MemberService;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 멤버 회원가입
     *
     * @param request
     * @return 응답 데이터와 상태 코드 반환
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterDto> registerMember(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(memberService.registerMember(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> loginMember(@Valid @RequestBody LoginRequest request) {
        return new ResponseEntity<>(memberService.LoginMember(request), HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    @AuthCheck({"OWNER", "CUSTOMER"})
    public ResponseEntity<Void> DeleteMember(@PathVariable Long memberId,
                                             @RequestParam String password,
                                             HttpServletRequest request) {
        memberService.deleteMember(memberId, password, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
