package team11.team11project.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team11.team11project.review.dto.request.ReviewAddRequestDto;

@Entity
@Table(name = "review")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    /**
     * store 연관관계 코드 삭제.
     * 어차피 orders 에서 타고 들어가면 store 정보가 있어서 과한 매핑이다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders; // 주문 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Member customer; // 리뷰 적은 유저 정보

    @Column(nullable = false)
    private int rating; // 별점 (1~5)

    private String comment; // 리뷰 내용

    // FIX : GetReview를 없애고 getCreateAt으로 교체

    // 리뷰 생성 메서드 추가
    public static Review createReview(ReviewAddRequestDto dto, Store store, Orders orders, Member member) {
        return new Review(
                null,
                store,
                orders,
                member,
                dto.getRating(),
                dto.getComment()
        );
    }

}
