package team11.team11project.store.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class UpdateStoreRequest {

    @NotBlank(message = "")
    private final String name;
    @NotNull(message = "")
    private final LocalTime openTime;
    @NotNull(message = "")
    private final LocalTime closeTime;
    @NotNull(message = "")
    private final int minOrderPrice;

}
