package br.com.restaurant_reservation_api.core.users.mapper;

import br.com.restaurant_reservation_api.core.users.domain.dto.response.UsersDTO;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import org.springframework.stereotype.Service;

@Service
public class UsersDTOMapper {

    public UsersDTO convert(Users users){
        if (users==null) return null;
        return new UsersDTO(
                users.getId(),
                users.getName(),
                users.getEmail(),
                users.getRole(),
                users.getCreatedAt()
        );
    }
}
