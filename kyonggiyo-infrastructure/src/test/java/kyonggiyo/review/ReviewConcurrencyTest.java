package kyonggiyo.review;

import kyonggiyo.application.auth.domain.vo.UserInfo;
import kyonggiyo.application.review.port.inbound.ReviewCreateCommand;
import kyonggiyo.application.review.service.ReviewCommandService;
import kyonggiyo.application.restaurant.domain.entity.Restaurant;
import kyonggiyo.application.review.domain.entity.Review;
import kyonggiyo.application.user.domain.entity.User;
import kyonggiyo.fixture.RestaurantFixtures;
import kyonggiyo.restaurant.repository.RestaurantJpaRepository;
import kyonggiyo.review.repository.ReviewJpaRepository;
import kyonggiyo.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.random.RandomGenerator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ReviewConcurrencyTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewJpaRepository reviewRepository;

    @Autowired
    private RestaurantJpaRepository restaurantRepository;

    @Autowired
    private ReviewCommandService reviewCommandService;

    @Test
    void 식당_리뷰_생성_동시성_테스트() throws InterruptedException {
        User user = userRepository.save(new User("tester"));
        UserInfo userInfo = new UserInfo(user.getId(), user.getRole());
        Restaurant restaurant = restaurantRepository.save(RestaurantFixtures.generateRestaurantDomainWithoutReview());

        int count = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            int rating = RandomGenerator.getDefault().nextInt(1, 5);
            executorService.submit(() -> {
                try {
                    reviewCommandService.createReview(userInfo, restaurant.getId(),
                            new ReviewCreateCommand(rating, "good", List.of("url")));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        // then
        List<Review> savedReviews = reviewRepository.findAll();
        assertThat(savedReviews.size()).isEqualTo(count);
    }

}
