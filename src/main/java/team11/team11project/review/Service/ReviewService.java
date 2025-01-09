package team11.team11project.review.Service;

import team11.team11project.review.dto.request.ReviewAddRequestDto;
import team11.team11project.review.dto.response.ReviewAddResponseDto;

import java.util.List;

public interface ReviewService {

    ReviewAddResponseDto addReview(Long orderId, ReviewAddRequestDto dto);

    List<ReviewAddResponseDto> findByReviewsByStoreId(Long storeId, int minRating, int maxRating);
}
