package br.com.restaurant_reservation_api.core.reservations.domain.dto.request;

import br.com.restaurant_reservation_api.core.reservationStatus.ReservationStatus;

import java.util.Date;

public record ReservationsFilterDTO(
        Long userId,
        Long restaurantTableId,
        Date reservationDate,
        ReservationStatus reservationStatus
) {
}
