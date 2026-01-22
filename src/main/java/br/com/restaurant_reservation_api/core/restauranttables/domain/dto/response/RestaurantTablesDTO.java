package br.com.restaurant_reservation_api.core.restauranttables.domain.dto.response;

import br.com.restaurant_reservation_api.core.statustables.StatusTables;

import java.time.LocalDateTime;

public record RestaurantTablesDTO(
        Long id,
        Integer tableNumber,
        Integer capacity,
        StatusTables statusTables,
        LocalDateTime createdAt
) {
}
