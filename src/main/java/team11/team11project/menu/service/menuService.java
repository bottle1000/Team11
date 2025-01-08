package team11.team11project.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import team11.team11project.common.entity.Menu;
import team11.team11project.menu.model.response.menuResponse;
import team11.team11project.menu.repository.menuRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class menuService {

    private final menuRepository menuRepository;

    //메뉴 생성
    public menuResponse createMenu(String name, Integer price, String description) {
        Menu menu = new Menu(name, price, description);

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

        if(name == null || name.isEmpty()) {
            name = menu.getName();
        }else{
            menu.setName(name);
        }

        if(price == null || price < 0 ){
            price = menu.getPrice();
        }else{
            menu.setPrice(price);
        }

        if(description == null || description.isEmpty()) {
            description = menu.getDescription();
        }else{
            menu.setDescription(description);
        }

        menuRepository.save(menu);
        return new menuResponse(id, name, price, description, LocalDate.now());

    }
}
