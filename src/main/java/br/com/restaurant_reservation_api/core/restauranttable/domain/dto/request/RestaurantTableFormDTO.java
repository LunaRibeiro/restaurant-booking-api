package br.com.restaurant_reservation_api.core.restauranttable.domain.dto.request;

import br.com.restaurant_reservation_api.core.status.Status;
import jakarta.validation.constraints.NotNull;

public record RestaurantTableFormDTO(

        @NotNull
        Integer tableNumber,

        @NotNull
        Integer capacity,
        Status status

) {
}
