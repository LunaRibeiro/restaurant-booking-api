package br.com.restaurant_reservation_api.core.users.mapper;

import br.com.restaurant_reservation_api.core.users.domain.dto.request.UsersFormDTO;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import org.springframework.stereotype.Service;

@Service
public class UsersCreateMapper {

    public Users convert(UsersFormDTO usersFormDTO) {
        Users users = new Users();
        users.setName(usersFormDTO.name());
        users.setEmail(usersFormDTO.email());
        users.setPassword(usersFormDTO.password());
        users.setRole(usersFormDTO.role());

        return users;
    }
}