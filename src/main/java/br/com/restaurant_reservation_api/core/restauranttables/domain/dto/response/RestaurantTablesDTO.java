package br.com.restaurant_reservation_api.core.restauranttables.domain.dto.response;

import br.com.restaurant_reservation_api.core.status.Status;
import java.time.LocalDateTime;

public record RestaurantTablesDTO(
        Long id,
        Integer tableNumber,
        Integer capacity,
        Status status,
        LocalDateTime createdAt
) {
}
