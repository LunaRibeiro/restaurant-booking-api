package br.com.restaurant_reservation_api.core.reservations.mapper;

import br.com.restaurant_reservation_api.core.reservations.domain.dto.response.ReservationsDTO;
import br.com.restaurant_reservation_api.core.reservations.domain.entity.Reservations;
import br.com.restaurant_reservation_api.core.restauranttables.mapper.RestaurantTablesDTOMapper;
import br.com.restaurant_reservation_api.core.users.mapper.UsersDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationsDTOMapper {

    private final UsersDTOMapper usersDTOMapper;
    private final RestaurantTablesDTOMapper restaurantTablesDTOMapper;

    public ReservationsDTO convert(Reservations reservations) {
        return new ReservationsDTO(
                reservations.getId(),
                usersDTOMapper.convert(reservations.getUsers()),
                restaurantTablesDTOMapper.convert(reservations.getRestaurantTables()),
                reservations.getReservationDate(),
                reservations.getReservationStatus(),
                reservations.getCreatedAt()
        );
    }
}
