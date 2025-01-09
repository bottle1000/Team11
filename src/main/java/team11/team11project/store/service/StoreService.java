package team11.team11project.store.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team11.team11project.common.entity.Member;
import team11.team11project.common.entity.Store;
import team11.team11project.store.model.request.CreateStoreRequest;
import team11.team11project.store.model.response.CreateStoreDto;
import team11.team11project.store.repository.StoreRepository;
import team11.team11project.user.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public CreateStoreDto createStore(CreateStoreRequest dto, HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ValidationException("존재하지 않는 유저 입니다."));

        validStoreLimit(member.getMemberName());
        Store store = Store.createStore(dto, member);
        storeRepository.save(store);
        return CreateStoreDto.convertDto("가게 생성 완료!",store);
    }

    /**
     * 같은 사장님 이름으로 가게는 최대 3개까지 가능한 검증 로직
     * @param memberName
     */
    private void validStoreLimit(String memberName) {
        int storesByMemberName = storeRepository.findStoresByMemberName(memberName);
        if (storesByMemberName >= 3){
            throw new IllegalStateException("가게 생성은 3개가 최대입니다!");
        }
    }
}
