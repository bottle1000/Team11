package team11.team11project.store.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class CreateStoreRequest {
    @NotBlank(message = "가게 이름 입력은 필수 입니다.")
    @Size(min = 2, max = 20, message = "가게 이름은 2자 이상, 20자 이하여야합니다.")
    private String storeName;

    // todo LocalTime에도 @NotBlank 사용이 안 됩니다ㅠ
//    @NotBlank(message = "오픈 시간 입력은 필수 입니다.")
    private LocalTime openTime;
//    @NotBlank(message = "클로즈 시간 입력은 필수 입니다.")
    private LocalTime closeTime;

    // todo int 값에는 @NotBlank 어노테이션을 사용할 수 없습니다!
    // 사장님한테 최소 설정 금액을 요구하려면 @MIN 사용하세요
//    @NotBlank(message = "최소 주문 금액 입력은 필수 입니다.")
    private int minOrderPrice;
}
