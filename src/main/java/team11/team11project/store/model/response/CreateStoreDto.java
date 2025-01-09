package team11.team11project.store.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team11.team11project.common.entity.Store;

@AllArgsConstructor
@Getter
public class CreateStoreDto {
    private String message;
    private Long storeId;

    public static CreateStoreDto convertDto(String message, Store store) {
        return new CreateStoreDto(
                message,
                store.getId()
        );
    }
}
