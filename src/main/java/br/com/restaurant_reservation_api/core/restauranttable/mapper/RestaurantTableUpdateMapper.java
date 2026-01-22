package br.com.restaurant_reservation_api.core.restauranttable.mapper;

import br.com.restaurant_reservation_api.core.restauranttable.domain.dto.request.RestaurantTableFormDTO;
import br.com.restaurant_reservation_api.core.restauranttable.domain.entity.RestaurantTable;

public class RestaurantTableUpdateMapper {

    public void update(RestaurantTable restaurantTable, RestaurantTableFormDTO restaurantTableFormDTO) {
        restaurantTable.setStatus(restaurantTableFormDTO.status());
        restaurantTable.setTableNumber(restaurantTableFormDTO.tableNumber());
        restaurantTable.setCapacity(restaurantTableFormDTO.capacity());
    }
}
