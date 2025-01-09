package team11.team11project.review.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team11.team11project.common.entity.Member;
import team11.team11project.common.entity.Orders;
import team11.team11project.common.entity.Review;
import team11.team11project.order.repository.OrderRepository;
import team11.team11project.review.dto.request.ReviewAddRequestDto;
import team11.team11project.review.dto.response.ReviewAddResponseDto;
import team11.team11project.review.repository.ReviewRepository;
import team11.team11project.user.repository.MemberRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewAddResponseDto addReview(Long orderId, ReviewAddRequestDto dto) {
        Orders order = orderRepository.findById(orderId).orElseThrow();
        Member customer = memberRepository.findById(customerId).orElseThrow(); // Todo 로그인된 사용자를 가져와야함
        Review review = new Review(order, customer , dto.getRating(), dto.getComment());
        review = reviewRepository.save(review);

        return ReviewAddResponseDto.toDto(review);
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
