package team11.team11project.store.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team11.team11project.common.entity.Store;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class StoreResponse {

    private Long storeId;
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int minOrderPrice;

    public static StoreResponse convertDto(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getOpenTime(),
                store.getCloseTime(),
                store.getMinOrderPrice()
        );
    }
}
