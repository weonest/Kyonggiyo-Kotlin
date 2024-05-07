package kyonggiyo.review.repository;

import kyonggiyo.application.review.event.entity.ReviewEvent;

import java.util.List;

public interface ReviewEventRepository {

    ReviewEvent save(ReviewEvent event);

    ReviewEvent getById(Long id);

    List<ReviewEvent> findAllFailedEvent();

}
