package team11.team11project.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindByRatingReviewDto {
    private int minRating = 1;
    private int maxRating = 5;
    private int page = 0;
    private int size = 10;

}