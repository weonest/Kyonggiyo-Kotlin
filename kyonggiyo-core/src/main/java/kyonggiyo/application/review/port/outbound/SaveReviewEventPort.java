package kyonggiyo.application.review.port.outbound;

import kyonggiyo.application.review.event.entity.ReviewEvent;

public interface SaveReviewEventPort {

    ReviewEvent save(ReviewEvent event);

}
