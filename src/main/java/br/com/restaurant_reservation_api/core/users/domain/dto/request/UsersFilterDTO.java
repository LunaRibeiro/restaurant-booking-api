package br.com.restaurant_reservation_api.core.users.domain.dto.request;

import br.com.restaurant_reservation_api.core.role.Role;

public record UsersFilterDTO(
        String name,
        String email,
        Role role
) {
}
