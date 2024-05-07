package kyonggiyo.application.restaurant.service;

import kyonggiyo.application.restaurant.port.inbound.CreateRestaurantUseCase;
import kyonggiyo.application.restaurant.port.inbound.RestaurantCreateCommand;
import kyonggiyo.application.restaurant.port.outbound.SaveRestaurantPort;
import kyonggiyo.application.restaurant.domain.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantCommandService implements CreateRestaurantUseCase {

    private final SaveRestaurantPort saveRestaurantPort;

    @Override
    public void createRestaurant(RestaurantCreateCommand command) {
        Restaurant restaurant = command.toEntity();
        saveRestaurantPort.save(restaurant);
    }

}
