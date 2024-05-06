package kyonggiyo.review.repository;

import kyonggiyo.common.exception.GlobalErrorCode;
import kyonggiyo.common.exception.NotFoundException;
import kyonggiyo.application.review.event.entity.ReviewEvent;
import kyonggiyo.review.repository.ReviewEventJpaRepository;
import kyonggiyo.review.repository.ReviewEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaReviewEventRepositoryImpl implements ReviewEventRepository {

    private final ReviewEventJpaRepository reviewEventJpaRepository;

    @Override
    public ReviewEvent save(ReviewEvent event) {
        return reviewEventJpaRepository.save(event);
    }

    @Override
    public ReviewEvent getById(Long id) {
        return reviewEventJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GlobalErrorCode.NOT_FOUND_ENTITY_EXCEPTION));
    }

    @Override
    public List<ReviewEvent> findAllFailedEvent() {
        return reviewEventJpaRepository.findAllByStatus(false);
    }

}
