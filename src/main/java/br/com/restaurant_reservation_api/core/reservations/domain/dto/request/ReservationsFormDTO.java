package br.com.restaurant_reservation_api.core.reservations.domain.dto.request;

import br.com.restaurant_reservation_api.core.reservationStatus.ReservationStatus;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ReservationsFormDTO(

        @NotNull
        Long userId,

        @NotNull
        Long restaurantTableId,

        @NotNull
        Date reservationDate,
        ReservationStatus reservationStatus
) {
}
