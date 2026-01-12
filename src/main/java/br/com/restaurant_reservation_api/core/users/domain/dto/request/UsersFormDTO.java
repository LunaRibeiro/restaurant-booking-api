package br.com.restaurant_reservation_api.core.users.domain.dto.request;

import br.com.restaurant_reservation_api.core.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsersFormDTO(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotNull
        Role role
) {
}
