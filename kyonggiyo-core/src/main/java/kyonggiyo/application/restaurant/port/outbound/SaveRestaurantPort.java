package kyonggiyo.application.restaurant.port.outbound;

import kyonggiyo.application.restaurant.domain.entity.Restaurant;

public interface SaveRestaurantPort {

    Restaurant save(Restaurant restaurant);

}

