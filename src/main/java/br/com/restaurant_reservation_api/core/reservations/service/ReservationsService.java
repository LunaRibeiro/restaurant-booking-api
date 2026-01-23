package br.com.restaurant_reservation_api.core.reservations.service;

import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import br.com.restaurant_reservation_api.common.specification.SpecificationHelper;
import br.com.restaurant_reservation_api.core.reservationStatus.ReservationStatus;
import br.com.restaurant_reservation_api.core.reservations.domain.dto.request.ReservationsFilterDTO;
import br.com.restaurant_reservation_api.core.reservations.domain.dto.request.ReservationsFormDTO;
import br.com.restaurant_reservation_api.core.reservations.domain.dto.response.ReservationsDTO;
import br.com.restaurant_reservation_api.core.reservations.domain.entity.Reservations;
import br.com.restaurant_reservation_api.core.reservations.mapper.ReservationsCreateMapper;
import br.com.restaurant_reservation_api.core.reservations.mapper.ReservationsDTOMapper;
import br.com.restaurant_reservation_api.core.reservations.repository.ReservationsRepository;
import br.com.restaurant_reservation_api.core.reservations.specification.ReservationsSpecification;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;
    private final ReservationsCreateMapper reservationsCreateMapper;
    private final ReservationsDTOMapper reservationsDTOMapper;

    public Reservations save(Reservations reservations) {
        return reservationsRepository.save(reservations);
    }

    public Reservations generateReservations(ReservationsFormDTO reservationsFormDTO, Users users, RestaurantTables restaurantTables) {
        return reservationsCreateMapper.convert(reservationsFormDTO, users, restaurantTables);
    }

    public ReservationsDTO generateReservationsDTO(Reservations reservations) {
        return reservationsDTOMapper.convert(reservations);
    }

    public List<Reservations> list(ReservationsFilterDTO filterDTO) {
        Specification<Reservations> reservationsSpecification = generateSpecification(filterDTO);
        return reservationsRepository.findAll(reservationsSpecification);
    }

    public Page<Reservations> list(ReservationsFilterDTO filterDTO, Pageable pageable) {
        Specification<Reservations> reservationsSpecification = generateSpecification(filterDTO);
        return reservationsRepository.findAll(reservationsSpecification, pageable);
    }

    public Specification<Reservations> generateSpecification(ReservationsFilterDTO filterDTO) {
        SearchCriteria<Long> userIdCriteria = SpecificationHelper.generateEqualsCriteria("userId", filterDTO.userId());
        SearchCriteria<Long> restaurantTableIdCriteria = SpecificationHelper.generateEqualsCriteria("restaurantTableId", filterDTO.restaurantTableId());
        SearchCriteria<Date> reservationDateIdCriteria = SpecificationHelper.generateEqualsCriteria("reservationDate", filterDTO.reservationDate());
        SearchCriteria<ReservationStatus> reservationStatusCriteria = SpecificationHelper.generateEqualsCriteria("reservationStatus", filterDTO.reservationStatus());

        ReservationsSpecification userIdSpecification = new ReservationsSpecification(userIdCriteria);
        ReservationsSpecification restaurantTableIdSpecification = new ReservationsSpecification(restaurantTableIdCriteria);
        ReservationsSpecification reservationDateIdSpecification = new ReservationsSpecification(reservationDateIdCriteria);
        ReservationsSpecification reservationStatusSpecification = new ReservationsSpecification(reservationStatusCriteria);

        return Specification.where(userIdSpecification)
                .and(restaurantTableIdSpecification)
                .and(reservationDateIdSpecification)
                .and(reservationStatusSpecification);
    }

    public Page<ReservationsDTO> generateReservationsDTOPage(Page<Reservations> reservationsPage) {
        return reservationsPage.map(this::generateReservationsDTO);
    }

    public List<ReservationsDTO> generateReservationsDTOList(List<Reservations> reservationsList) {
        return reservationsList.stream().map(this::generateReservationsDTO).toList();
    }

    public Reservations getOrThrowException(Long id) {
        return reservationsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservations")
        );
    }

    public Reservations getOrNull(Long id){
        if (id == null) return null;
        return reservationsRepository.findById(id).orElse(null);
    }

    public ReservationsDTO cancel(Reservations reservations) {
        if (ReservationStatus.CANCELLED.equals(reservations.getReservationStatus())) {
            return generateReservationsDTO(reservations);
        }

        reservations.setReservationStatus(ReservationStatus.ACTIVE);
        save(reservations);

        return generateReservationsDTO(reservations);
    }
}
