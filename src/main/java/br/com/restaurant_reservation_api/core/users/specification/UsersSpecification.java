package br.com.restaurant_reservation_api.core.users.specification;

import br.com.restaurant_reservation_api.common.specification.BaseSpecification;
import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;

public class UsersSpecification extends BaseSpecification<Users> {
    public UsersSpecification(SearchCriteria<?> searchCriteria){ super(searchCriteria); }
}
