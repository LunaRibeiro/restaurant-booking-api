package br.com.restaurant_reservation_api.core.restauranttable.mapper;

import br.com.restaurant_reservation_api.core.restauranttable.domain.dto.request.RestaurantTableFormDTO;
import br.com.restaurant_reservation_api.core.restauranttable.domain.entity.RestaurantTable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantTableCreateMapper {

    public RestaurantTable convert(RestaurantTableFormDTO restaurantTableFormDTO) {
        RestaurantTable restaurantTable = new RestaurantTable();

        restaurantTable.setTableNumber(restaurantTableFormDTO.tableNumber());
        restaurantTable.setCapacity(restaurantTableFormDTO.capacity());
        restaurantTable.setStatus(restaurantTableFormDTO.status());

        return restaurantTable;
    }

}
