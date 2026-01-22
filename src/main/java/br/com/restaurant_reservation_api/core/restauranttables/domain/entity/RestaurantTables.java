package br.com.restaurant_reservation_api.core.restauranttables.domain.entity;

import br.com.restaurant_reservation_api.core.statustables.StatusTables;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer tableNumber;

    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private StatusTables statusTables;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private LocalDateTime createdAt;

}
