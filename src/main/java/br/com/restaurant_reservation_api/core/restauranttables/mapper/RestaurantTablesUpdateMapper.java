package br.com.restaurant_reservation_api.core.restauranttables.mapper;

import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request.RestaurantTablesFormDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import org.springframework.stereotype.Service;

@Service
public class RestaurantTablesUpdateMapper {

    public void update(RestaurantTables restaurantTables, RestaurantTablesFormDTO restaurantTablesFormDTO) {
        restaurantTables.setStatusTables(restaurantTablesFormDTO.statusTables());
        restaurantTables.setTableNumber(restaurantTablesFormDTO.tableNumber());
        restaurantTables.setCapacity(restaurantTablesFormDTO.capacity());
    }
}
