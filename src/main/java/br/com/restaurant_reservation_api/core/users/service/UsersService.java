package br.com.restaurant_reservation_api.core.users.service;

import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import br.com.restaurant_reservation_api.common.specification.SpecificationHelper;
import br.com.restaurant_reservation_api.core.role.Role;
import br.com.restaurant_reservation_api.core.users.domain.dto.request.UsersFilterDTO;
import br.com.restaurant_reservation_api.core.users.domain.dto.request.UsersFormDTO;
import br.com.restaurant_reservation_api.core.users.domain.dto.response.UsersDTO;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import br.com.restaurant_reservation_api.core.users.mapper.UsersCreateMapper;
import br.com.restaurant_reservation_api.core.users.mapper.UsersDTOMapper;
import br.com.restaurant_reservation_api.core.users.mapper.UsersUpdateMapper;
import br.com.restaurant_reservation_api.core.users.repository.UsersRepository;
import br.com.restaurant_reservation_api.core.users.specification.UsersSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Users> list(UsersFilterDTO usersFilterDTO){
        Specification<Users> userSpecification = generateSpecification(usersFilterDTO);
        return usersRepository.findAll(userSpecification);
    }

    public Page<Users> list(UsersFilterDTO usersFilterDTO, Pageable pageable){
        Specification<Users> userSpecification = generateSpecification(usersFilterDTO);
        return usersRepository.findAll(userSpecification, pageable);
    }

    private Specification<Users> generateSpecification(UsersFilterDTO usersFilterDTO){
        SearchCriteria<String> nameCriteria = SpecificationHelper.generateEqualsCriteria("name", usersFilterDTO.name());
        SearchCriteria<String> emailCriteria = SpecificationHelper.generateEqualsCriteria("email", usersFilterDTO.email());
        SearchCriteria<Role> roleCriteria = SpecificationHelper.generateEqualsCriteria("role", usersFilterDTO.role());

        UsersSpecification nameSpecification = new UsersSpecification(nameCriteria);
        UsersSpecification emailSpecification = new UsersSpecification(emailCriteria);
        UsersSpecification roleSpecification = new UsersSpecification(roleCriteria);

        return Specification.where(nameSpecification)
                .and(emailSpecification)
                .and(roleSpecification);
    }

    public Page<UsersDTO> generateUsersDTOPage(Page<Users> usersPage){
        return usersPage.map(this::generateUsersDTO);
    }

    public List<UsersDTO> generateUsersDTOList(List<Users> usersList){
        return usersList.stream().map(usersDTOMapper::convert).toList();
    }

    public Users getOrThrowException(Long id) {
        return usersRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Users")
        );
    }
}
