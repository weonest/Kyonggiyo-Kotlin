package kyonggiyo.persistence.restaurant.review;

import kyonggiyo.common.exception.GlobalErrorCode;
import kyonggiyo.common.exception.NotFoundException;
import kyonggiyo.application.review.domain.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaReviewRepositoryImpl implements ReviewRepository {

    private final ReviewJpaRepository repository;

    @Override
    public Review getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(GlobalErrorCode.NOT_FOUND_ENTITY_EXCEPTION));
    }

    @Override
    public Review save(Review review) {
        return repository.save(review);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
