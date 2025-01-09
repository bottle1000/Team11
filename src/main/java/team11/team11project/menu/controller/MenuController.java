package team11.team11project.menu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team11.team11project.menu.model.dto.Dto;
import team11.team11project.menu.model.response.MenuResponse;
import team11.team11project.menu.service.MenuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class MenuController {

    private final MenuService menuService;

    //메뉴 생성
    @PostMapping("/{storeId}/menus")
    public ResponseEntity<MenuResponse> createMenu(@PathVariable Long storeId, @RequestParam Long ownerId, @RequestBody Dto menu) {

        MenuResponse menuResponse = menuService.createMenu(storeId, ownerId, menu.getName(), menu.getPrice(), menu.getDescription());

        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    //메뉴 수정
    @PatchMapping("/{storeId}/menus/{id}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long storeId,@RequestParam Long ownerId, @PathVariable Long id, @RequestBody Dto menu) {
        MenuResponse menuResponse = menuService.updateMenu(storeId, ownerId, id, menu.getName(), menu.getPrice(), menu.getDescription());
        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    //메뉴 삭제
    @DeleteMapping("/{storeId}/menus/{id}")
    public ResponseEntity<MenuResponse> deleteMenu(@PathVariable Long storeId,@RequestParam Long ownerId, @PathVariable Long id) {

        menuService.deleteMenu(storeId, ownerId, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
