package team11.team11project.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderRequest {

    // 속성
    //todo int, Long 타입 -> @NotBlank 제거 (주석 처리)
    // @Size -> min(1) @Size 어노테이션과 int는 타입이 달라 검사가 불가능해서 에러가 납니다..

    //fix.    @NotBlank(message = "수량 입력은 필수 입니다.")
    //@Size(min = 1, message = "수량은 1개 이상이여야합니다.")
    @Min(1)
    private final int quantity;

    //    @NotBlank
    private final Long customer_id;
    //    @NotBlank
    private final Long menu_id;

    // 생성자

    // 기능
}
