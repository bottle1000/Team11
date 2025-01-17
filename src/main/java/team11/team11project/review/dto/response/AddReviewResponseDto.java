package team11.team11project.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team11.team11project.common.entity.Review;

@AllArgsConstructor
@Getter
public class AddReviewResponseDto {

    // FIX : API 명세서에서 응답 데이터로 고객 이름이 있어서 추가해줬습니다.
    private Long reviewId;
    private int rating;
    private String comment;
    private String customerName;


    public static AddReviewResponseDto toDto(Review review) {
        return new AddReviewResponseDto(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getCustomer().getMemberName()
        );
    }
}
