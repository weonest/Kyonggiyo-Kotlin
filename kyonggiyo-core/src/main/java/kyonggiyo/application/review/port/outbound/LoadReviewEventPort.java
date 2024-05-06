package kyonggiyo.application.review.port.outbound;

import kyonggiyo.application.review.event.entity.ReviewEvent;

import java.util.List;

public interface LoadReviewEventPort {

    ReviewEvent getById(Long id);

    List<ReviewEvent> findAllFailedEvent();

}
