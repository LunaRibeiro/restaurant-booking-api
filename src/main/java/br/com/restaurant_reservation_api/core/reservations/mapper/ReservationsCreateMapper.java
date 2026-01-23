package br.com.restaurant_reservation_api.core.reservations.mapper;

import br.com.restaurant_reservation_api.core.reservationStatus.ReservationStatus;
import br.com.restaurant_reservation_api.core.reservations.domain.dto.request.ReservationsFormDTO;
import br.com.restaurant_reservation_api.core.reservations.domain.entity.Reservations;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import org.springframework.stereotype.Service;

@Service
public class ReservationsCreateMapper {

    public Reservations convert(ReservationsFormDTO reservationsFormDTO, Users users, RestaurantTables restaurantTables) {
        Reservations reservations = new Reservations();
        reservations.setUserId(users);
        reservations.setRestaurantTableId(restaurantTables);
        reservations.setReservationDate(reservationsFormDTO.reservationDate());
        reservations.setReservationStatus(ReservationStatus.PENDING);

        return reservations;
    }
}
