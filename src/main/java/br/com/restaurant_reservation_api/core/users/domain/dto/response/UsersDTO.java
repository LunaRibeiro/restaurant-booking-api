package br.com.restaurant_reservation_api.core.users.domain.dto.response;

import br.com.restaurant_reservation_api.core.role.Role;

public record UsersDTO(
        Long id,
        String name,
        String email,
        String password,
        Role role
) {
}
