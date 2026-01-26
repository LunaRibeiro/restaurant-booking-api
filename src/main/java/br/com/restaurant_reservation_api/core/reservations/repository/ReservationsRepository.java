package br.com.restaurant_reservation_api.core.reservations.repository;

import br.com.restaurant_reservation_api.core.reservationStatus.ReservationStatus;
import br.com.restaurant_reservation_api.core.reservations.domain.entity.Reservations;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long>, JpaSpecificationExecutor<Reservations> {

    boolean existsByRestaurantTablesAndReservationDateAndReservationStatus(
            RestaurantTables restaurantTables,
            LocalDateTime reservationDate,
            ReservationStatus reservationStatus
    );
}

