package kyonggiyo.application.review.port.inbound;

import kyonggiyo.application.auth.domain.vo.UserInfo;

public interface CreateReviewUseCase {

    void createReview(UserInfo userInfo,
                      Long restaurantId,
                      ReviewCreateCommand command);

}
