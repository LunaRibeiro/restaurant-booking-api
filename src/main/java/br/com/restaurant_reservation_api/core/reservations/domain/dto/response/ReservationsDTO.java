package br.com.restaurant_reservation_api.core.reservations.domain.dto.response;

import br.com.restaurant_reservation_api.core.reservationStatus.ReservationStatus;
import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.response.RestaurantTablesDTO;
import br.com.restaurant_reservation_api.core.users.domain.dto.response.UsersDTO;

import java.time.LocalDateTime;
import java.util.Date;

public record ReservationsDTO(
        Long id,
        UsersDTO userId,
        RestaurantTablesDTO restaurantTableId,
        Date reservationDate,
        ReservationStatus reservationStatus,
        LocalDateTime createdAt
) {
}
