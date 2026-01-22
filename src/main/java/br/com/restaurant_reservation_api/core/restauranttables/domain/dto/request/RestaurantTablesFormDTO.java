package br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request;

import br.com.restaurant_reservation_api.core.statustables.StatusTables;
import jakarta.validation.constraints.NotNull;

public record RestaurantTablesFormDTO(

        @NotNull
        Integer tableNumber,

        @NotNull
        Integer capacity,
        StatusTables statusTables

) {
}
