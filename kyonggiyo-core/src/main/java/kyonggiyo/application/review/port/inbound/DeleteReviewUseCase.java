package kyonggiyo.application.review.port.inbound;


import kyonggiyo.application.auth.domain.vo.UserInfo;

public interface DeleteReviewUseCase {

    void deleteReview(UserInfo userInfo, Long id);

}
