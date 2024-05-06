package kyonggiyo.application.restaurant.port.inbound;


public interface CreateRestaurantUseCase {

    void createRestaurant(RestaurantCreateCommand command);

}
