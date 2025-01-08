package team11.team11project.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team11.team11project.common.enums.OrderStatus;

@Getter
@RequiredArgsConstructor
public class CreateOrderRequest {

    // 속성
    @NotBlank
    private final int quantity;
    @NotNull(message = "Order status is required.")
    private final OrderStatus orderStatus;

    @NotBlank
    private final Long customer_id;
    @NotBlank
    private final Long menu_id;

    // 생성자

    // 기능
}
