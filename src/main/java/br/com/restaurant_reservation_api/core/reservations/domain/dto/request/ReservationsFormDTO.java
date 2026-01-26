package br.com.restaurant_reservation_api.core.reservations.domain.dto.request;

import br.com.restaurant_reservation_api.core.reservationStatus.ReservationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record ReservationsFormDTO(

        @NotNull
        Long userId,

        @NotNull
        Long restaurantTableId,

        @NotNull
        LocalDateTime reservationDate,
        ReservationStatus reservationStatus
) {
}
