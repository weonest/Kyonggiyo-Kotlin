package kyonggiyo.application.restaurant.service;

import kyonggiyo.application.image.port.inbound.ImageResponse;
import kyonggiyo.application.restaurant.port.inbound.LoadRestaurantUseCase;
import kyonggiyo.application.restaurant.port.inbound.RestaurantByKeywordQuery;
import kyonggiyo.application.restaurant.port.inbound.RestaurantCategoryQuery;
import kyonggiyo.application.restaurant.port.inbound.RestaurantMarkerResponse;
import kyonggiyo.application.restaurant.port.inbound.RestaurantResponse;
import kyonggiyo.application.restaurant.port.inbound.RestaurantSearchResponse;
import kyonggiyo.application.review.port.inbound.ReviewResponse;
import kyonggiyo.application.image.port.outbound.LoadImagePort;
import kyonggiyo.application.restaurant.port.outbound.LoadRestaurantPort;
import kyonggiyo.application.image.domain.entity.Image;
import kyonggiyo.application.image.domain.vo.ImageType;
import kyonggiyo.application.restaurant.domain.entity.Restaurant;
import kyonggiyo.application.review.domain.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantQueryService implements LoadRestaurantUseCase {

    private final LoadRestaurantPort loadRestaurantPort;
    private final LoadImagePort loadImagePort;

    @Override
    public RestaurantResponse getById(Long id) {
        Restaurant restaurant = loadRestaurantPort.getById(id);

        Set<Review> reviews = restaurant.getReviews();

        List<Long> reviewIds = reviews.stream()
                .map(v -> v.getId())
                .toList();

        Map<Long, List<Image>> images = loadImagePort.findAllByImageTypeAndReferenceIdIn(ImageType.REVIEW, reviewIds)
                .stream()
                .collect(Collectors.groupingBy(Image::getReferenceId));

        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(v -> {
                    List<ImageResponse> imageResponses = Optional.ofNullable(images.get(v.getId()))
                            .filter(Predicate.not(List::isEmpty))
                            .map(list -> list.stream().map(ImageResponse::from).toList())
                            .orElse(null);
                    return ReviewResponse.of(v, imageResponses);
                }).toList();

        return RestaurantResponse.of(restaurant, reviewResponses);
    }

    @Override
    public List<RestaurantMarkerResponse> getAllRestaurantsForMarker() {
        List<Restaurant> restaurants = loadRestaurantPort.findAll();
        return restaurants.stream()
                .map(RestaurantMarkerResponse::from).toList();
    }

    @Override
    public List<RestaurantSearchResponse> searchByKeyword(RestaurantByKeywordQuery query) {
        List<Restaurant> restaurants = loadRestaurantPort.findByNameOrReviewContent(query.keyword());
        return restaurants.stream()
                .map(RestaurantSearchResponse::from).toList();
    }

    @Override
    public List<RestaurantSearchResponse> filterRestaurants(RestaurantCategoryQuery query) {
        List<Restaurant> restaurants = loadRestaurantPort.filterByCategory(query.categories());
        return restaurants.stream()
                .map(RestaurantSearchResponse::from).toList();
    }

}
