package br.com.restaurant_reservation_api.core.reservations.repository;

import br.com.restaurant_reservation_api.core.reservations.domain.entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long>, JpaSpecificationExecutor<Reservations> {
}
