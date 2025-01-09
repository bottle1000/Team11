package team11.team11project.store.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class UpdateStoreRequest {

    @NotBlank(message = "")
    private final String name;
    @NotBlank(message = "")
    private final LocalTime openTime;
    @NotBlank(message = "")
    private final LocalTime closeTime;
    @NotBlank(message = "")
    private final int minOrderPrice;

}
