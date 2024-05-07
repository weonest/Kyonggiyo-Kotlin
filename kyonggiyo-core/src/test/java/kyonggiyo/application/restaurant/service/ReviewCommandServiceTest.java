package kyonggiyo.application.restaurant.service;

import jakarta.persistence.EntityManager;
import kyonggiyo.application.auth.domain.vo.UserInfo;
import kyonggiyo.application.review.port.inbound.ReviewCreateCommand;
import kyonggiyo.application.review.port.inbound.ReviewUpdateCommand;
import kyonggiyo.application.restaurant.port.outbound.LoadRestaurantPort;
import kyonggiyo.application.review.port.outbound.DeleteReviewPort;
import kyonggiyo.application.review.port.outbound.LoadReviewPort;
import kyonggiyo.application.review.port.outbound.SaveReviewPort;
import kyonggiyo.application.user.port.outbound.LoadUserPort;
import kyonggiyo.application.service.ServiceTest;
import kyonggiyo.application.image.service.ImageService;
import kyonggiyo.application.review.service.ReviewCommandService;
import kyonggiyo.application.restaurant.domain.entity.Restaurant;
import kyonggiyo.application.review.domain.entity.Review;
import kyonggiyo.application.user.domain.entity.User;
import kyonggiyo.fixture.RestaurantFixtures;
import kyonggiyo.fixture.UserFixtures;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = ReviewCommandService.class)
class ReviewCommandServiceTest extends ServiceTest {

    @Autowired
    private ReviewCommandService reviewCommandService;

    @MockBean
    private LoadUserPort loadUserPort;
    @MockBean
    private LoadRestaurantPort loadRestaurantPort;
    @MockBean
    private LoadReviewPort loadReviewPort;
    @MockBean
    private SaveReviewPort saveReviewPort;
    @MockBean
    private DeleteReviewPort deleteReviewPort;
    @MockBean
    private ImageService imageService;
    @MockBean
    private EntityManager entityManager;

    @Test
    void 유저의_요청을_통해_리뷰를_생성한다() {
        // given
        User reviewer = UserFixtures.INSTANCE.generateEntity();
        UserInfo userInfo = new UserInfo(reviewer.getId(), reviewer.getRole());
        Restaurant restaurant = RestaurantFixtures.generateRestaurantEntityWithoutReview();
        ReviewCreateCommand request = Instancio.create(ReviewCreateCommand.class);

        Review review = Review.builder()
                .rating(request.rating())
                .content(request.content())
                .restaurant(restaurant)
                .reviewerId(reviewer.getId())
                .reviewerNickname(reviewer.getNickname())
                .build();

        given(loadUserPort.getById(userInfo.userId)).willReturn(reviewer);
        given(loadRestaurantPort.getById(restaurant.getId())).willReturn(restaurant);
        given(saveReviewPort.save(review)).willReturn(review);

        // when
        reviewCommandService.createReview(userInfo, restaurant.getId(), request);

        // then
        verify(loadUserPort, only()).getById(userInfo.userId);
        verify(loadRestaurantPort, only()).getById(restaurant.getId());
        verify(saveReviewPort, only()).save(review);
    }

    @Test
    void 작성자의_요청을_통해_리뷰를_수정한다() {
        // given
        User reviewer = UserFixtures.INSTANCE.generateEntity();
        UserInfo userInfo = new UserInfo(reviewer.getId(), reviewer.getRole());
        Restaurant restaurant = RestaurantFixtures.generateRestaurantEntityWithoutReview();
        ReviewUpdateCommand request = Instancio.create(ReviewUpdateCommand.class);

        Review review = Review.builder()
                .rating(request.rating())
                .content(request.content())
                .restaurant(restaurant)
                .reviewerId(reviewer.getId())
                .reviewerNickname(reviewer.getNickname())
                .build();

        given(loadReviewPort.getById(any())).willReturn(review);

        // when
        reviewCommandService.updateReview(userInfo, restaurant.getId(), request);

        // then
        verify(loadReviewPort, only()).getById(any());
    }

    @Test
    void 작성자의_요청을_통해_리뷰를_삭제한다() {
        // given
        User reviewer = UserFixtures.INSTANCE.generateEntity();
        UserInfo userInfo = new UserInfo(reviewer.getId(), reviewer.getRole());
        Restaurant restaurant = RestaurantFixtures.generateRestaurantEntityWithoutReview();
        ReviewUpdateCommand request = Instancio.create(ReviewUpdateCommand.class);

        Review review = Review.builder()
                .rating(request.rating())
                .content(request.content())
                .restaurant(restaurant)
                .reviewerId(reviewer.getId())
                .reviewerNickname(reviewer.getNickname())
                .build();

        given(loadReviewPort.getById(any())).willReturn(review);
        willDoNothing().given(deleteReviewPort).deleteById(any());
        willDoNothing().given(imageService).deleteByImageTypeAndReferenceId(any(), any());

        // when
        reviewCommandService.deleteReview(userInfo, review.getId());

        // then
        verify(loadReviewPort, only()).getById(any());
        verify(deleteReviewPort, only()).deleteById(any());
        verify(imageService, only()).deleteByImageTypeAndReferenceId(any(), any());
    }

}