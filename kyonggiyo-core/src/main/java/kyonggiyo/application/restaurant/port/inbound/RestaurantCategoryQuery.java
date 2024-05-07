package kyonggiyo.application.restaurant.port.inbound;

import kyonggiyo.application.restaurant.domain.vo.RestaurantCategory;

import java.util.List;

public record RestaurantCategoryQuery(
        List<RestaurantCategory> categories
) {
}
