package kyonggiyo.application.review.port.outbound;

import kyonggiyo.application.review.domain.entity.Review;

public interface LoadReviewPort {

    Review getById(Long id);

}
