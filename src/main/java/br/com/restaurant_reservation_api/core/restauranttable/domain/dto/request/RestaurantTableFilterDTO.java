package br.com.restaurant_reservation_api.core.restauranttable.domain.dto.request;

import br.com.restaurant_reservation_api.core.status.Status;

public record RestaurantTableFilterDTO(
        Integer tableNumber,
        Integer capacity,
        Status status
) {
}
