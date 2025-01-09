package team11.team11project.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team11.team11project.common.entity.Review;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ReviewAddResponseDto {

    private Long id;
    private int rating;
    private String comment;
    private LocalDateTime reviewTime;

    public static ReviewAddResponseDto toDto(Review review) {
        return new ReviewAddResponseDto(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getReviewTime()
        );
    }
}
