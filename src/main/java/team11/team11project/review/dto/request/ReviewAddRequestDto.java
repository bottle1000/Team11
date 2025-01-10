package team11.team11project.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewAddRequestDto {

    private int rating;
    private String comment;
    //추가
    private Long customerId;

}
