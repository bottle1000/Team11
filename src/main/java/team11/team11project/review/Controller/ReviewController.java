package team11.team11project.review.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team11.team11project.review.Service.ReviewService;
import team11.team11project.review.dto.request.ReviewAddRequestDto;
import team11.team11project.review.dto.response.ReviewAddResponseDto;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor

public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     *
     * @param dto 별점, 리뷰 내용
     * @return
     */
    @PostMapping("/order/{orderId}/reviews")
    public ResponseEntity<ReviewAddResponseDto> addReview(@PathVariable Long orderId, @RequestBody ReviewAddRequestDto dto) {
        return new ResponseEntity<>(reviewService.addReview(orderId, dto), HttpStatus.CREATED);
    }

    /**
     * @return 가게 전체 리뷰 조회
     */
    @GetMapping("/stores/{storeId}/reviews")
    public ResponseEntity<List<ReviewAddResponseDto>> findReviewsByStore(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") int minRating,
            @RequestParam(defaultValue = "5") int maxRating) {
        List<ReviewAddResponseDto> reviews = reviewService.findByReviewsByStoreId(storeId, minRating, maxRating);

        return ResponseEntity.ok(reviews);
    }
}
