package kyonggiyo.persistence.restaurant;

import kyonggiyo.application.restaurant.port.outbound.LoadRestaurantPort;
import kyonggiyo.application.restaurant.port.outbound.SaveRestaurantPort;
import kyonggiyo.application.restaurant.domain.entity.Restaurant;
import kyonggiyo.application.restaurant.domain.vo.RestaurantCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantPersistenceAdapter implements LoadRestaurantPort, SaveRestaurantPort {

    private final RestaurantRepository repository;

    @Override
    public Restaurant getById(Long id) {
        Restaurant restaurant = repository.getById(id);
        return restaurant;
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Restaurant> findByNameOrReviewContent(String keyword) {
        return repository.findByNameOrReviewContent(keyword);
    }

    @Override
    public List<Restaurant> filterByCategory(List<RestaurantCategory> categories) {
        return repository.filterByCategory(categories);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

}
