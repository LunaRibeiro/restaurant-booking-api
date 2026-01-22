package br.com.restaurant_reservation_api.core.restauranttable.domain.dto.response;

import br.com.restaurant_reservation_api.core.status.Status;
import java.time.LocalDateTime;

public record RestaurantTableDTO(
        Long id,
        Integer tableNumber,
        Integer capacity,
        Status status,
        LocalDateTime createdAt
) {
}
