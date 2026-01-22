package br.com.restaurant_reservation_api.core.restauranttables.repository;

import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTablesRepository extends JpaRepository<RestaurantTables, Long>, JpaSpecificationExecutor<RestaurantTables> {
}
