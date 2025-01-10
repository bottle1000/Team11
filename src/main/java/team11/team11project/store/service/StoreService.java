package team11.team11project.store.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team11.team11project.common.entity.Member;
import team11.team11project.common.entity.Menu;
import team11.team11project.common.entity.Store;
import team11.team11project.common.exception.InvalidStoreOwnerException;
import team11.team11project.common.exception.NotFoundException;
import team11.team11project.common.exception.StoreLimitExceededException;
import team11.team11project.menu.model.response.StoreMenuResponse;
import team11.team11project.menu.repository.MenuRepository;
import team11.team11project.store.model.request.CreateStoreRequest;
import team11.team11project.store.model.request.UpdateStoreRequest;
import team11.team11project.store.model.response.OneStoreResponse;
import team11.team11project.store.model.response.StoreResponse;
import team11.team11project.store.repository.StoreRepository;
import team11.team11project.user.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;

    // 가게 생성 서비스
    public StoreResponse createStore(CreateStoreRequest dto, HttpServletRequest request) {

        Long ownerId = (Long) request.getAttribute("memberId");
        Member owner = memberRepository.findById(ownerId).orElseThrow(() ->
                new NotFoundException("존재하지 않는 유저 입니다."));

        validStoreLimit(owner.getId());

        Store store = Store.createStore(dto, owner);

        Store savedStore = storeRepository.save(store);

        return StoreResponse.convertDto(savedStore);
    }

    // 가게 전체 조회 서비스
    public Page<StoreResponse> findAllStore(Pageable pageable, String name) {

        if (!name.isEmpty()) {
            Page<Store> storePage = storeRepository.findStoresByName(name, pageable);

            return storePage.map(StoreResponse::convertDto);
        }

        Page<Store> allStore = storeRepository.findAll(pageable);

        return allStore.map(StoreResponse::convertDto);
    }

    // 가게 단건 조회 서비스
    public OneStoreResponse findOneStore(Long storeId) {

        Store foundStore = storeRepository.findById(storeId).orElseThrow(() ->
                new NotFoundException("가게가 존재하지 않습니다."));

        List<Menu> menuList = menuRepository.findByStoreId(foundStore.getId());

        List<StoreMenuResponse> menuResponseList = menuList.stream()
                .map(StoreMenuResponse::convertDto)
                .toList();

        return new OneStoreResponse(
                foundStore.getId(),
                foundStore.getName(),
                foundStore.getOpenTime(),
                foundStore.getCloseTime(),
                foundStore.getMinOrderPrice(),
                menuResponseList
        );
    }

    // 가게 수정 서비스
    @Transactional
    public StoreResponse updateStore(Long storeId, UpdateStoreRequest storeRequest, HttpServletRequest request) {

        Long ownerId = (Long) request.getAttribute("memberId");

        Store foundStore = storeRepository.findById(storeId).orElseThrow(() ->
                new NotFoundException("존재하지 않는 가게입니다."));

        // 해당 가게의 사장님이 아닌 사람이 가게를 수정하려고 하는 경우 예외 처리
        if (!(ownerId == foundStore.getOwner().getId())) {
            throw new InvalidStoreOwnerException("해당 가게의 사장님이 아닙니다.");
        }

        foundStore.updateStore(
                storeRequest.getName(),
                storeRequest.getOpenTime(),
                storeRequest.getCloseTime(),
                storeRequest.getMinOrderPrice()
        );

        return StoreResponse.convertDto(foundStore);
    }

    // 가게 폐업 서비스
    public void deleteStore(Long storeId, HttpServletRequest request) {

        Long ownerId = (Long) request.getAttribute("memberId");

        Store foundStore = storeRepository.findById(storeId).orElseThrow(() ->
                new NotFoundException("존재하지 않는 가게입니다."));

        // 해당 가게의 사장님이 아닌 사람이 가게를 폐업하려고 하는 경우 예외 처리
        if (!(ownerId == foundStore.getOwner().getId())) {
            throw new InvalidStoreOwnerException("해당 가게의 사장님이 아닙니다.");
        }

        storeRepository.delete(foundStore);
    }

    /**
     * 한 사장님은 최대 3개까지 가게 생성이 가능한 검증 로직
     * @param ownerId
     */
    private void validStoreLimit(Long ownerId) {
        int storesByMemberId = storeRepository.findStoresByMemberId(ownerId);
        if (storesByMemberId >= 3){
            throw new StoreLimitExceededException("가게 생성은 3개가 최대입니다!");
        }
    }
}
