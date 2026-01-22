package br.com.restaurant_reservation_api.core.restauranttables.specification;

import br.com.restaurant_reservation_api.common.specification.BaseSpecification;
import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;

public class RestaurantTablesSpecification extends BaseSpecification<RestaurantTables> {
    public RestaurantTablesSpecification(SearchCriteria<?> searchCriteria){ super(searchCriteria); }
}
