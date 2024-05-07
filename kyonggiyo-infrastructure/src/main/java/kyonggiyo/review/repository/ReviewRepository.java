package kyonggiyo.review.repository;

import kyonggiyo.application.review.domain.entity.Review;

public interface ReviewRepository {

    Review getById(Long id);

    Review save(Review review);

    void deleteById(Long id);

}
