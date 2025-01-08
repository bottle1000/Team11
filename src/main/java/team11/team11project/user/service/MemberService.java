package team11.team11project.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team11.team11project.common.encode.PasswordEncoder;
import team11.team11project.common.entity.Member;
import team11.team11project.common.exception.DuplicateEmailException;
import team11.team11project.user.model.request.RegisterRequest;
import team11.team11project.user.model.response.RegisterDto;
import team11.team11project.user.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterDto registerMember(RegisterRequest request) {
        //비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = Member.createMember(request, encodedPassword);

        //이메일 중복 검사
        boolean validateEmail = memberRepository.existsByEmail(request.getEmail());
        if (validateEmail) {
            throw new DuplicateEmailException("이미 가입된 이메일 입니다.");
        }

        memberRepository.save(member);
        return RegisterDto.convertDto(member);
    }
}
