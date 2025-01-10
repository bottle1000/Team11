package team11.team11project.store.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team11.team11project.menu.model.response.StoreMenuResponse;

import java.time.LocalTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class OneStoreResponse {

    private final Long id;
    private final String name;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final int minOrderPrice;
    private final List<StoreMenuResponse> menu;
}
