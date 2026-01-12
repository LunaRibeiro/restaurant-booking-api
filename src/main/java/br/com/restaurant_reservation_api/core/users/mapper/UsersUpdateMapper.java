package br.com.restaurant_reservation_api.core.users.mapper;

import br.com.restaurant_reservation_api.core.users.domain.dto.request.UsersFormDTO;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import org.springframework.stereotype.Service;

@Service
public class UsersUpdateMapper {

    public void update (Users users, UsersFormDTO usersFormDTO){
        users.setName(usersFormDTO.name());
        users.setEmail(usersFormDTO.email());
        users.setPassword(usersFormDTO.password());
        users.setRole(usersFormDTO.role());
    }
}