package kyonggiyo.review.adapter;

import kyonggiyo.application.review.port.outbound.DeleteReviewPort;
import kyonggiyo.application.review.port.outbound.LoadReviewPort;
import kyonggiyo.application.review.port.outbound.SaveReviewPort;
import kyonggiyo.application.review.domain.entity.Review;
import kyonggiyo.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewPersistenceAdapter implements SaveReviewPort, LoadReviewPort, DeleteReviewPort {

    private final ReviewRepository reviewRepository;

    @Override
    public Review getById(Long id) {
        return reviewRepository.getById(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

}
