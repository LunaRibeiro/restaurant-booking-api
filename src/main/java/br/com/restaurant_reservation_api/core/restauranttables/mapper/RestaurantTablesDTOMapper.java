package br.com.restaurant_reservation_api.core.restauranttables.mapper;

import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.response.RestaurantTablesDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import org.springframework.stereotype.Service;

@Service
public class RestaurantTablesDTOMapper {

    public RestaurantTablesDTO convert(RestaurantTables restaurantTables) {
        if (restaurantTables == null) { return null; }
        return new RestaurantTablesDTO(
                restaurantTables.getId(),
                restaurantTables.getTableNumber(),
                restaurantTables.getCapacity(),
                restaurantTables.getStatus(),
                restaurantTables.getCreatedAt()
        );
    }
}
