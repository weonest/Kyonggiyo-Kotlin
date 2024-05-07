package kyonggiyo.application.restaurant.port.inbound;

import kyonggiyo.application.restaurant.domain.entity.Restaurant;
import kyonggiyo.application.restaurant.domain.vo.RestaurantCategory;

public record RestaurantMarkerResponse(
        Long id,
        String name,
        float averageRating,
        RestaurantCategory category,
        double lat,
        double lng,
        String reason
) {

    public static RestaurantMarkerResponse from(Restaurant restaurant) {
        return new RestaurantMarkerResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAverageRating(),
                restaurant.getCategory(),
                restaurant.getAddress().getLat(),
                restaurant.getAddress().getLng(),
                restaurant.getReason()
        );
    }

}
