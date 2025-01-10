package team11.team11project.review.Service;

import org.springframework.data.domain.Pageable;
import team11.team11project.review.dto.request.ReviewAddRequestDto;
import team11.team11project.review.dto.response.ReviewAddResponseDto;

import java.util.List;

public interface ReviewService {

    ReviewAddResponseDto addReview(Long orderId, ReviewAddRequestDto dto);

    List<ReviewAddResponseDto> findByReviewsById(Long storeId, int minRating, int maxRating, Pageable pageable);
}
