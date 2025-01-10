package team11.team11project.menu.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team11.team11project.common.entity.Menu;

@Getter
@RequiredArgsConstructor
public class StoreMenuResponse {

    private final Long id;
    private final String name;
    private final int price;
    private final String description;

    public static StoreMenuResponse convertDto(Menu menu) {
        return new StoreMenuResponse(
                menu.getId(),
                menu.getName(),
                menu.getPrice(),
                menu.getDescription()
        );
    }
}
