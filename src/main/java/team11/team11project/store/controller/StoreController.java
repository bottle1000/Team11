package team11.team11project.store.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team11.team11project.common.aspect.AuthCheck;
import team11.team11project.store.model.request.CreateStoreRequest;
import team11.team11project.store.model.response.CreateStoreDto;
import team11.team11project.store.service.StoreService;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 생성
     * 권한 : OWNER
     * @return
     */
    @PostMapping
    @AuthCheck("OWNER")
    public ResponseEntity<CreateStoreDto> createStore(@RequestBody CreateStoreRequest dto,
                                                      HttpServletRequest request) {
        return new ResponseEntity<>(storeService.createStore(dto, request),HttpStatus.CREATED);
    }

    /**
     * 가게 수정
     * 권한 : OWNER
     * @return
     */
//    @PostMapping
//    public ResponseEntity<> updateStore() {
//
//    }

}
