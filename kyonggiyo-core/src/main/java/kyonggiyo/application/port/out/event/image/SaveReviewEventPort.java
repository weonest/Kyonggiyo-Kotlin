package kyonggiyo.application.port.out.event.image;

import kyonggiyo.application.review.event.entity.ReviewEvent;

public interface SaveReviewEventPort {

    ReviewEvent save(ReviewEvent event);

}
