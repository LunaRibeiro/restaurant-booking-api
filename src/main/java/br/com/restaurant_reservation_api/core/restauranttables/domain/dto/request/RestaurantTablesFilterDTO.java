package br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request;

import br.com.restaurant_reservation_api.core.statustables.StatusTables;

public record RestaurantTablesFilterDTO(
        Integer tableNumber,
        Integer capacity,
        StatusTables statusTables
) {
}
