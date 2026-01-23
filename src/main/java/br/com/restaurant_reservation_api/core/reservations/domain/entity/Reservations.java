package br.com.restaurant_reservation_api.core.reservations.domain.entity;

import br.com.restaurant_reservation_api.core.reservationStatus.ReservationStatus;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "restaurant_table_id", nullable = false)
    private RestaurantTables restaurantTableId;

    @Column(nullable = false)
    private Date reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    private LocalDateTime createdAt;

}
