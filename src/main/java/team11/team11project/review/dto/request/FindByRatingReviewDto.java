package team11.team11project.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindByRatingReviewDto {
    @Min(value = 1, message = "최소 별점은 1 부터 입니다.")
    private int minRating = 1;
    @Max(value = 5, message = "최대 별점은 5 까지 입니다.")
    private int maxRating = 5;
    @Min(value = 0)
    private int page = 0;
    @Max(value = 10)
    private int size = 10;

}