package team11.team11project.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    /**
     * store 연관관계 코드 삭제.
     * 어차피 orders 에서 타고 들어가면 store 정보가 있어서 과한 매핑이다.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders; // 주문 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Member customer; // 리뷰 적은 유저 정보

    @Column(nullable = false)
    private int rating; // 별점 (1~5)

    private String comment; // 리뷰 내용

    @Column(nullable = false)
    private LocalDateTime reviewTime; // 리뷰 작성 시간
}
