package br.com.restaurant_reservation_api.core.restauranttable.mapper;

import br.com.restaurant_reservation_api.core.restauranttable.domain.dto.response.RestaurantTableDTO;
import br.com.restaurant_reservation_api.core.restauranttable.domain.entity.RestaurantTable;

public class RestaurantTableDTOMapper {

    public RestaurantTableDTO convert(RestaurantTable restaurantTable) {
        if (restaurantTable == null) { return null; }
        return new RestaurantTableDTO(
                restaurantTable.getId(),
                restaurantTable.getTableNumber(),
                restaurantTable.getCapacity(),
                restaurantTable.getStatus(),
                restaurantTable.getCreatedAt()
        );
    }
}
