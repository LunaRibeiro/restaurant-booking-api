package br.com.restaurant_reservation_api.core.users.domain.dto.response;

import br.com.restaurant_reservation_api.core.role.Role;
import java.time.LocalDateTime;

public record UsersDTO(
        Long id,
        String name,
        String email,
        Role role,
        LocalDateTime createdAt
) {
}
