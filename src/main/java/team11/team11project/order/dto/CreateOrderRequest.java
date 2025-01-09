package team11.team11project.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderRequest {

    // 속성
    @NotBlank
    private final int quantity;

    @NotBlank
    private final Long customer_id;
    @NotBlank
    private final Long menu_id;

    // 생성자

    // 기능
}
