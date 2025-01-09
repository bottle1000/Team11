package team11.team11project.review.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team11.team11project.common.entity.Review;
import team11.team11project.review.dto.request.ReviewAddRequestDto;
import team11.team11project.review.dto.request.ReviewAddResponseDto;
import team11.team11project.review.repository.ReviewRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewAddResponseDto addReview(ReviewAddRequestDto dto) {

        return null;
    }

    @Override
    public List<ReviewAddResponseDto> findByReviewsByStoreId(Long storeId, int minRating, int maxRating) {
        List<Review> reviews = reviewRepository.findAllByStoreId(storeId);

        return reviews.stream()
                .filter(review -> review.getRating() >= minRating && review.getRating() <= maxRating) // 별점 범위 필터링
                .sorted(Comparator.comparing(Review::getReviewTime).reversed()) // 최신순 정렬
                .map(ReviewAddResponseDto::toDto) // DTO 변환
                .collect(Collectors.toList());
    }

}
