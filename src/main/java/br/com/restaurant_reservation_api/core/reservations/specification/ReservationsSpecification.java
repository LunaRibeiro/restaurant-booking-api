package br.com.restaurant_reservation_api.core.reservations.specification;

import br.com.restaurant_reservation_api.common.specification.BaseSpecification;
import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import br.com.restaurant_reservation_api.core.reservations.domain.entity.Reservations;

public class ReservationsSpecification extends BaseSpecification<Reservations> {
    public ReservationsSpecification(SearchCriteria<?> searchCriteria) { super(searchCriteria);}
}
