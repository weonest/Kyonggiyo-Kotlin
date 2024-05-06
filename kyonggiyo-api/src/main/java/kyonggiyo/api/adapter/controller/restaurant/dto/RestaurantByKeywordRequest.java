package kyonggiyo.api.adapter.controller.restaurant.dto;

import kyonggiyo.application.restaurant.port.inbound.RestaurantByKeywordQuery;

public record RestaurantByKeywordRequest (
        String keyword
) {

    public RestaurantByKeywordQuery toQuery() {
        return new RestaurantByKeywordQuery(
                keyword
        );
    }

}
