package kyonggiyo.review.repository;

import kyonggiyo.application.review.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
}
