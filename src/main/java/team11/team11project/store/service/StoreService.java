package team11.team11project.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team11.team11project.common.entity.Store;
import team11.team11project.store.model.request.CreateStoreRequest;
import team11.team11project.store.model.response.CreateStoreDto;
import team11.team11project.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    //Todo : 오너의 이름으로 가게가 3개 이상이면 예외를 던져 더 이상 만들지 못하게 하기.
    public CreateStoreDto createStore(CreateStoreRequest request) {
        Store store = Store.createStore(request);
        storeRepository.save(store);
        return CreateStoreDto.convertDto("가게 생성 완료!",store);
    }
}
