package br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request;

import br.com.restaurant_reservation_api.core.status.Status;

public record RestaurantTablesFilterDTO(
        Integer tableNumber,
        Integer capacity,
        Status status
) {
}
