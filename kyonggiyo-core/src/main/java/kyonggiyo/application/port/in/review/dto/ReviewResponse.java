package kyonggiyo.application.port.in.review.dto;

import kyonggiyo.application.image.port.inbound.ImageResponse;
import kyonggiyo.application.port.in.user.dto.UserResponse;
import kyonggiyo.domain.review.Review;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewResponse(
        Long id,
        int rating,
        String content,
        LocalDateTime createdAt,
        UserResponse reviewer,
        List<ImageResponse> images
) {

    public static ReviewResponse of(Review review, List<ImageResponse> images) {
        return new ReviewResponse(
                review.getId(),
                review.getRating(),
                review.getContent(),
                review.getCreatedAt(),
                new UserResponse(review.getReviewerId(),
                        review.getReviewerNickname()),
                images
        );
    }

}
