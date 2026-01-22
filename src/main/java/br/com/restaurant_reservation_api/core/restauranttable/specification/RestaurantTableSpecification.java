package br.com.restaurant_reservation_api.core.restauranttable.specification;

import br.com.restaurant_reservation_api.common.specification.BaseSpecification;
import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import br.com.restaurant_reservation_api.core.restauranttable.domain.entity.RestaurantTable;

public class RestaurantTableSpecification extends BaseSpecification<RestaurantTable> {
    public RestaurantTableSpecification(SearchCriteria<?> searchCriteria){ super(searchCriteria); }
}
