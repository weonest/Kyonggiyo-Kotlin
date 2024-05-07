package kyonggiyo.application.restaurant.port.inbound;

import kyonggiyo.application.restaurant.domain.entity.Restaurant;
import kyonggiyo.application.restaurant.domain.vo.RestaurantCategory;

public record RestaurantCreateCommand(
        String name,
        RestaurantCategory category,
        String contact,
        String address,
        double lat,
        double lng,
        String reason
){

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .category(category)
                .contact(contact)
                .address(address)
                .lat(lat)
                .lng(lng)
                .reason(reason)
                .build();
    }

}