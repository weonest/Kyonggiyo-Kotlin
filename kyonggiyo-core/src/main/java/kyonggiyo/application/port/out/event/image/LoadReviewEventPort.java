package kyonggiyo.application.port.out.event.image;

import kyonggiyo.application.review.event.entity.ReviewEvent;

import java.util.List;

public interface LoadReviewEventPort {

    ReviewEvent getById(Long id);

    List<ReviewEvent> findAllFailedEvent();

}
