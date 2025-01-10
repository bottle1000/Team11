package team11.team11project.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team11.team11project.common.entity.Menu;
import team11.team11project.common.entity.Store;
import team11.team11project.common.exception.MissingRequiredFieldException;
import team11.team11project.common.exception.NotFoundException;
import team11.team11project.menu.model.response.MenuResponse;
import team11.team11project.menu.repository.MenuRepository;
import team11.team11project.store.repository.StoreRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    //메뉴 생성
    public MenuResponse createMenu(Long storeId, Long ownerId, String name, Integer price, String description) {

        Store store = storeRepository.findById(storeId).orElseThrow(()-> new NotFoundException("가게를 찾을 수 없습니다."));

        if (!store.getOwner().getId().equals(ownerId)){
            throw new NotFoundException("사장만 가능합니다.");
        }

        Menu menu = new Menu(name, price, description, store, ownerId);

        //값이 없을 때 예외 처리
        if(name == null || name.isEmpty()) {
            throw new MissingRequiredFieldException("메뉴 이름을 입력하세요.");
        }else if(price == null || price < 0){
            throw new MissingRequiredFieldException("가격을 입력하세요.");
        }else if(description == null || description.isEmpty()) {
            throw new MissingRequiredFieldException("내용을 입력하세요.");
        }

        Menu savedMenu = menuRepository.save(menu);
        return new MenuResponse(savedMenu.getId(), savedMenu.getName(), savedMenu.getPrice(), savedMenu.getDescription(), savedMenu.getCreatedAt());

    }

    //메뉴 수정
    public MenuResponse updateMenu(Long storeId, Long ownerId, Long id, String name, Integer price, String description) {
        Menu menu = menuRepository.findMenuById(id).orElseThrow(() -> new NotFoundException("메뉴를 찾을 수 없습니다."));

        Store store = storeRepository.findById(storeId).orElseThrow(()->new NotFoundException("가게를 찾을 수 없습니다."));
        if (!store.getOwner().getId().equals(ownerId)){
            throw new NotFoundException("사장만 가능합니다.");
        }

        //입력 값이 null 또는 비어 있을 경우 기존 값 설정
        if(name != null && !name.isEmpty()) {
            menu.setName(name);
        }

        if(price != null && price >= 0 ){
            menu.setPrice(price);
        }

        if(description != null && !description.isEmpty()) {
            menu.setDescription(description);
        }

        Menu savedMenu = menuRepository.save(menu);
        return new MenuResponse(savedMenu.getId(), savedMenu.getName(), savedMenu.getPrice(), savedMenu.getDescription(), LocalDate.now());

    }

    //메뉴 삭제
    public void deleteMenu(Long storeId, Long ownerId, Long id) {
        Menu menu = menuRepository.findMenuById(id).orElseThrow(() -> new NotFoundException("메뉴를 찾을 수 없습니다."));

        Store store = storeRepository.findById(storeId).orElseThrow(()->new NotFoundException("가게를 찾을 수 없습니다."));
        if (!store.getOwner().getId().equals(ownerId)){
            throw new NotFoundException("사장만 가능합니다.");
        }

        menuRepository.delete(menu);
    }

}
