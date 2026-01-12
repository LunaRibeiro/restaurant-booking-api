package br.com.restaurant_reservation_api.core.users.service;

import br.com.restaurant_reservation_api.core.users.domain.dto.request.UsersFormDTO;
import br.com.restaurant_reservation_api.core.users.domain.dto.response.UsersDTO;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import br.com.restaurant_reservation_api.core.users.mapper.UsersCreateMapper;
import br.com.restaurant_reservation_api.core.users.mapper.UsersDTOMapper;
import br.com.restaurant_reservation_api.core.users.mapper.UsersUpdateMapper;
import br.com.restaurant_reservation_api.core.users.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersCreateMapper usersCreateMapper;
    private final UsersDTOMapper usersDTOMapper;
    private final UsersUpdateMapper usersUpdateMapper;

    public Users save(Users users){ return usersRepository.save(users); }

    public Users generateUsers(UsersFormDTO usersFormDTO){ return usersCreateMapper.convert(usersFormDTO); }

    public UsersDTO generateUsersDTO(Users users){ return usersDTOMapper.convert(users); }

    public void update(Users users, UsersFormDTO usersFormDTO){
        usersUpdateMapper.update(users, usersFormDTO);
    }

    public void delete(Users users){ usersRepository.delete(users); }

    public Users getOrNull(Long id){
        if (id == null) return null;
        return usersRepository.findById(id).orElse(null);
    }

}
