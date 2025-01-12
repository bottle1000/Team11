package team11.team11project.menu.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dto {
    @NotBlank(message = "메뉴 입력은 필수 입니다.")
    @Size(min = 2, message = "메뉴 이름은 2자 이상이여야합니다.")
    private String name;

    @NotNull(message = "가격 입력은 필수입니다.")
    @Min(value = 1, message = "가격은 1원 이상이여야 합니다.")
    private Integer price;

    @NotBlank(message = "메뉴 설명은 필수 입니다.")
    @Size(min = 2, message = "메뉴 이름은 2자 이상이여야합니다.")
    private String description;

    private LocalDate createdAt;

}
