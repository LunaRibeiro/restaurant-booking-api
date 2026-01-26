package br.com.restaurant_reservation_api.core.reservations.controller;

import br.com.restaurant_reservation_api.common.utils.HttpUtils;
import br.com.restaurant_reservation_api.core.reservations.domain.dto.request.ReservationsFilterDTO;
import br.com.restaurant_reservation_api.core.reservations.domain.dto.request.ReservationsFormDTO;
import br.com.restaurant_reservation_api.core.reservations.domain.dto.response.ReservationsDTO;
import br.com.restaurant_reservation_api.core.reservations.domain.entity.Reservations;
import br.com.restaurant_reservation_api.core.reservations.service.ReservationsService;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import br.com.restaurant_reservation_api.core.restauranttables.service.RestaurantTablesService;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import br.com.restaurant_reservation_api.core.users.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationsController {

    private final ReservationsService reservationsService;
    private final UsersService usersService;
    private final RestaurantTablesService restaurantTablesService;

    @GetMapping
    public ResponseEntity<Page<ReservationsDTO>> listPaged(ReservationsFilterDTO reservationsFilterDTO, Pageable pageable){
        Page<Reservations> reservationsPage = reservationsService.list(reservationsFilterDTO, pageable);
        Page<ReservationsDTO> reservationsDTOPage = reservationsService.generateReservationsDTOPage(reservationsPage);

        return ResponseEntity.ok(reservationsDTOPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationsDTO>> list(ReservationsFilterDTO reservationsFilterDTO){
        List<Reservations> reservationsList = reservationsService.list(reservationsFilterDTO);
        List<ReservationsDTO> reservationsDTOList = reservationsService.generateReservationsDTOList(reservationsList);

        return ResponseEntity.ok(reservationsDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationsDTO> get(@PathVariable Long id){
        Reservations reservations = reservationsService.getOrNull(id);
        if (reservations == null) {
            return ResponseEntity.notFound().build();
        }

        ReservationsDTO reservationsDTO = reservationsService.generateReservationsDTO(reservations);

        return ResponseEntity.ok(reservationsDTO);
    }

    @PostMapping
    public ResponseEntity<ReservationsDTO> create(@RequestBody ReservationsFormDTO reservationsFormDTO, UriComponentsBuilder uriComponentsBuilder){
        Users users = usersService.getOrThrowException(reservationsFormDTO.userId());
        RestaurantTables restaurantTables = restaurantTablesService.getOrThrowException(reservationsFormDTO.restaurantTableId());

        reservationsService.validateReservationDate(restaurantTables, reservationsFormDTO.reservationDate());
        Reservations reservations = reservationsService.generateReservations(reservationsFormDTO, users, restaurantTables);
        reservationsService.save(reservations);

        ReservationsDTO reservationsDTO = reservationsService.generateReservationsDTO(reservations);

        URI uri = HttpUtils.createURI(uriComponentsBuilder, "Reservations", reservations.getId());

        return ResponseEntity.created(uri).body(reservationsDTO);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationsDTO> cancelReservation(@PathVariable Long id) {
        Reservations reservations = reservationsService.getOrThrowException(id);
        ReservationsDTO dto = reservationsService.cancel(reservations);
        return ResponseEntity.ok(dto);
    }
}
