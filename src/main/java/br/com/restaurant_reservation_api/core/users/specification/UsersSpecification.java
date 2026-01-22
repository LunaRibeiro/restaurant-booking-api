package br.com.restaurant_reservation_api.core.users.specification;

import br.com.restaurant_reservation_api.common.specification.BaseSpecification;
import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import org.apache.catalina.User;

public class UsersSpecification extends BaseSpecification<User> {
    public UsersSpecification(SearchCriteria<?> searchCriteria){ super(searchCriteria); }
}
