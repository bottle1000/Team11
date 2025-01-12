package team11.team11project.review.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team11.team11project.review.dto.request.AddReviewRequestDto;
import team11.team11project.review.dto.response.AddReviewResponseDto;
import team11.team11project.review.dto.response.ReviewDto;

public interface ReviewService {

    AddReviewResponseDto addReview(Long orderId, AddReviewRequestDto dto, HttpServletRequest servletRequest);

    Page<ReviewDto> findByReviewsById(Long storeId, int minRating, int maxRating, Pageable pageable);
}
