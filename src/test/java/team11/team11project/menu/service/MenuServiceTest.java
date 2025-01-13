package team11.team11project.menu.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team11.team11project.common.encode.PasswordEncoder;
import team11.team11project.common.entity.Member;
import team11.team11project.common.entity.Menu;
import team11.team11project.common.entity.Store;
import team11.team11project.common.exception.MissingRequiredFieldException;
import team11.team11project.common.exception.NotFoundException;
import team11.team11project.menu.model.response.MenuResponse;
import team11.team11project.menu.repository.MenuRepository;
import team11.team11project.store.model.request.CreateStoreRequest;
import team11.team11project.store.repository.StoreRepository;
import team11.team11project.user.model.request.RegisterRequest;
import team11.team11project.user.repository.MemberRepository;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
public class MenuServiceTest {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Menu menu;
    private Store store;
    private Member owner;

    @BeforeEach
    void 메뉴_초기값_설정(){

        // Owner 객체 생성
        String encodedPassword = passwordEncoder.encode("Password1234!");
      owner = memberRepository.save(Member.createMember(
                new RegisterRequest(
                        "유저테스트",
                        "usertest@example.com",
                        "Password1234!",
                        "OWNER"),
                encodedPassword
        ));

        // Store 객체 생성
        LocalTime openTime = LocalTime.parse("10:00");
        LocalTime closeTime = LocalTime.parse("09:59");

        CreateStoreRequest storeRequest = new CreateStoreRequest(
                "치킨집",
                openTime,
                closeTime,
                10000
        );
        store = storeRepository.save(Store.createStore(storeRequest,owner));

        //menu 초기값
        menu = new Menu("치킨",10000,"치킨은 맛있다.",store, owner.getId());

        menuRepository.save(menu);
    }

    @Test
    @DisplayName("메뉴 생성 시 모든 값이 유효하면 생성된다.")
    void 메뉴_생성_테트스(){
        Long storeId = store.getId();
        Long ownerId = owner.getId();

        MenuResponse menuResponse= menuService.createMenu(storeId,ownerId,"짜장면",7000,"짜장면은 맛있다.");

        Assertions.assertEquals("짜장면", menuResponse.getName());
        Assertions.assertEquals(7000, menuResponse.getPrice());
        Assertions.assertEquals("짜장면은 맛있다.", menuResponse.getDescription());
    }


    @Test
    @DisplayName("수정 시 price가 0보다 작으면 예외가 발생한다.")
    void 메뉴_가격_수정_테스트(){
        Long menuId = menu.getId();
        Long storeId = store.getId();
        Long ownerId = owner.getId();

        assertThatThrownBy(() -> menuService.updateMenu(storeId,ownerId,menuId,"치킨",-1,"치킨은 맜있다.")).isInstanceOf(MissingRequiredFieldException.class);
    }

    @Test
    @DisplayName("수정 시 owner가 아니면 예외가 발생한다.")
    void 메뉴_사장_수정_테스트(){
        Long menuId = menu.getId();
        Long storeId = store.getId();
        Long ownerId = 2L;

        assertThatThrownBy(() -> menuService.updateMenu(storeId,ownerId,menuId,"치킨",15000,"치킨은 맜있다.")).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("수정 시 store가 없으면 예외가 발생한다.")
    void 메뉴_가게_수정_테스트(){
        Long menuId = menu.getId();
        Long storeId = 10L;
        Long ownerId = owner.getId();

        assertThatThrownBy(() -> menuService.updateMenu(storeId,ownerId,menuId,"치킨",15000,"치킨은 맜있다.")).isInstanceOf(NotFoundException.class);

    }

    @Test
    @DisplayName("수정 시 모든 값이 유효하면 정상적으로 수정된다.")
    void 메뉴_수정_테스트(){
        Long menuId = menu.getId();
        Long storeId = store.getId();
        Long ownerId = owner.getId();

        MenuResponse menuResponse= menuService.updateMenu(storeId,ownerId,menuId,"피자",18000,"피자도 맛있다.");

        Assertions.assertEquals("피자", menuResponse.getName());
        Assertions.assertEquals(18000, menuResponse.getPrice());
        Assertions.assertEquals("피자도 맛있다.", menuResponse.getDescription());
    }

}
