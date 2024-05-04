package kyonggiyo.application.review.port.inbound;

import kyonggiyo.application.auth.domain.vo.UserInfo;

public interface UpdateReviewUseCase {

    void updateReview(UserInfo userInfo,
                      Long id,
                      ReviewUpdateCommand command);

}
