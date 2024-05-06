package kyonggiyo.application.restaurant.port.inbound;


import java.util.List;

public interface LoadRestaurantUseCase {

    RestaurantResponse getById(Long id);

    List<RestaurantMarkerResponse> getAllRestaurantsForMarker();

    List<RestaurantSearchResponse> searchByKeyword(RestaurantByKeywordQuery query);

    List<RestaurantSearchResponse> filterRestaurants(RestaurantCategoryQuery param);

}
