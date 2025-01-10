package team11.team11project.review.Service;

import org.springframework.data.domain.Pageable;
import team11.team11project.review.dto.request.AddReviewRequestDto;
import team11.team11project.review.dto.response.AddReviewResponseDto;

import java.util.List;

public interface ReviewService {

    AddReviewResponseDto addReview(Long orderId, AddReviewRequestDto dto);

    List<AddReviewResponseDto> findByReviewsById(Long storeId, int minRating, int maxRating, Pageable pageable);
}
