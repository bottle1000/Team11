package team11.team11project.store.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class CreateStoreRequest {
    private String storeName;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int minOrderPrice;
}
