package team11.team11project.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import team11.team11project.common.entity.Menu;
import team11.team11project.menu.model.response.menuResponse;
import team11.team11project.menu.repository.menuRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class menuService {

    private final menuRepository menuRepository;

    //메뉴 생성
    public menuResponse createMenu(String name, Integer price, String description) {
        Menu menu = new Menu(name, price, description);

        //값이 없을 때 예외 처리
        if(name == null || name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty");
        }else if(price < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price cannot be negative");
        }else if(description == null || description.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description cannot be empty");
        }

        Menu savedMenu = menuRepository.save(menu);
        return new menuResponse(savedMenu.getId(), savedMenu.getName(), savedMenu.getPrice(), savedMenu.getDescription(), savedMenu.getCreatedAt());

    }

    //메뉴 수정
    public menuResponse updateMenu(Long id, String name, Integer price, String description) {
        Menu menu = menuRepository.findMenuById(id).orElseThrow(() -> new IllegalStateException("메뉴를 찾을 수 없습니다."));

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
        return new menuResponse(savedMenu.getId(), savedMenu.getName(), savedMenu.getPrice(), savedMenu.getDescription(), LocalDate.now());

    }

    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findMenuById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."));
        menuRepository.delete(menu);
    }

    public List<menuResponse> getMenus() {
      List<Menu> menus = menuRepository.findAll();

      return menus.stream().map(menuResponse::toDto).toList();

    }
}
