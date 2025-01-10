package team11.team11project.review.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team11.team11project.common.entity.Member;
import team11.team11project.common.entity.Orders;
import team11.team11project.common.entity.Review;
import team11.team11project.common.entity.Store;
import team11.team11project.common.exception.NotFoundException;
import team11.team11project.order.repository.OrderRepository;
import team11.team11project.review.dto.request.AddReviewRequestDto;
import team11.team11project.review.dto.response.AddReviewResponseDto;
import team11.team11project.review.repository.ReviewRepository;
import team11.team11project.store.repository.StoreRepository;
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
    private final StoreRepository storeRepository;


    @Override
    public AddReviewResponseDto addReview(Long orderId, AddReviewRequestDto dto) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("찾는 주문이 없습니다."));
        Store store = storeRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("찾는 가게가 없습니다."));
        Member customer = memberRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new NotFoundException("찾는 고객이 없습니다."));

        Review review = Review.createReview(dto, store ,order, customer);
        reviewRepository.save(review);

        return AddReviewResponseDto.toDto(review);
    }

    // FIX : GetReview를 없애고 getCreateAt으로 교체
    // TODO : 가게 정보를 다건으 조회는 하는데 리뷰를 별점 범위에 따라 조회. 따로 봐야하는건지 같이 봐야하는건지 잘 모르겠음.
    @Override
    public List<AddReviewResponseDto> findByReviewsById(Long storeId, int minRating, int maxRating, Pageable pageable) {
        List<Review> reviews = reviewRepository.findAllById(storeId);

        return reviews.stream()
                .filter(review -> review.getRating() >= minRating && review.getRating() <= maxRating) // 별점 범위 필터링
                .sorted(Comparator.comparing(Review::getCreatedAt).reversed()) // 최신순 정렬
                .map(AddReviewResponseDto::toDto) // DTO 변환
                .collect(Collectors.toList());
    }

}
