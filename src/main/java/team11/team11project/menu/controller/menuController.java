package team11.team11project.menu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import team11.team11project.menu.model.dto.dto;
import team11.team11project.menu.model.response.menuResponse;
import team11.team11project.menu.service.menuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class menuController {

    private final menuService menuService;

    //메뉴 생성
    @PostMapping("/menus")
    public ResponseEntity<menuResponse> createMenu(@RequestBody dto menu) {

        menuResponse menuResponse = menuService.createMenu(menu.getName(), menu.getPrice(), menu.getDescription());

        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    //메뉴 수정
    @PatchMapping("/menus/{id}")
    public ResponseEntity<menuResponse> updateMenu(@PathVariable Long id, @RequestBody dto menu) {
        menuResponse menuResponse = menuService.updateMenu(id, menu.getName(), menu.getPrice(), menu.getDescription());
        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    //메뉴 삭제
    @DeleteMapping("/menus/{id}")
    public ResponseEntity<menuResponse> deleteMenu(@PathVariable Long id) {

        menuService.deleteMenu(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    //삭제 됐는지 확인용 검색
    @GetMapping
    public ResponseEntity<List<menuResponse>> getMenus() {
        menuService.getMenus();
        return new ResponseEntity<>(menuService.getMenus(), HttpStatus.OK);
    }
}
