package kyonggiyo.review.adapter;

import kyonggiyo.application.review.port.outbound.LoadReviewEventPort;
import kyonggiyo.application.review.port.outbound.SaveReviewEventPort;
import kyonggiyo.application.review.event.entity.ReviewEvent;
import kyonggiyo.review.repository.ReviewEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewEventPersistenceAdapter implements SaveReviewEventPort, LoadReviewEventPort {

    private final ReviewEventRepository reviewEventRepository;

    @Override
    public ReviewEvent save(ReviewEvent event) {
        return reviewEventRepository.save(event);
    }

    @Override
    public ReviewEvent getById(Long id) {
        return reviewEventRepository.getById(id);
    }

    @Override
    public List<ReviewEvent> findAllFailedEvent() {
        return reviewEventRepository.findAllFailedEvent();
    }

}
