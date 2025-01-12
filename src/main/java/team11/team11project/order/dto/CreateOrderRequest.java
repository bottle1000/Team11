package team11.team11project.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderRequest {

    @NotNull(message = "수량 입력은 필수 입니다.")
    @Min(value = 1, message = "수량은 1개 이상이여야합니다.")
    private final int quantity;

    @NotNull
    private final Long customer_id;

    @NotNull
    private final Long menu_id;

    // 생성자

    // 기능
}
