package br.com.restaurant_reservation_api.core.restauranttables.mapper;

import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request.RestaurantTablesFormDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import br.com.restaurant_reservation_api.core.statustables.StatusTables;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantTablesCreateMapper {

    public RestaurantTables convert(RestaurantTablesFormDTO restaurantTablesFormDTO) {
        RestaurantTables restaurantTables = new RestaurantTables();

        restaurantTables.setTableNumber(restaurantTablesFormDTO.tableNumber());
        restaurantTables.setCapacity(restaurantTablesFormDTO.capacity());
        restaurantTables.setStatusTables(StatusTables.AVAILABLE);

        return restaurantTables;
    }

}
