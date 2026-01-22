package br.com.restaurant_reservation_api.core.users.controller;

import br.com.restaurant_reservation_api.common.utils.HttpUtils;
import br.com.restaurant_reservation_api.core.users.domain.dto.request.UsersFilterDTO;
import br.com.restaurant_reservation_api.core.users.domain.dto.request.UsersFormDTO;
import br.com.restaurant_reservation_api.core.users.domain.dto.response.UsersDTO;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import br.com.restaurant_reservation_api.core.users.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<Page<UsersDTO>> listPaged(Pageable pageable, UsersFilterDTO usersFilterDTO){
        Page<Users> usersPage = usersService.list(usersFilterDTO, pageable);
        Page<UsersDTO> usersDTOPage = usersService.generateUsersDTOPage(usersPage);

        return ResponseEntity.ok(usersDTOPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsersDTO>> list(UsersFilterDTO usersFilterDTO){
        List<Users> usersList = usersService.list(usersFilterDTO);
        List<UsersDTO> usersDTOList = usersService.generateUsersDTOList(usersList);

        return ResponseEntity.ok(usersDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> get(@PathVariable Long id){
        Users users = usersService.getOrNull(id);
        if(users == null) return ResponseEntity.notFound().build();

        UsersDTO usersDTO = usersService.generateUsersDTO(users);

        return ResponseEntity.ok(usersDTO);
    }

    @PostMapping
    public ResponseEntity<UsersDTO> create(@RequestBody @Valid UsersFormDTO usersFormDTO, UriComponentsBuilder uriComponentsBuilder) {

        Users users = usersService.generateUsers(usersFormDTO);
        usersService.save(users);

        UsersDTO usersDTO = usersService.generateUsersDTO(users);

        URI uri = HttpUtils.createURI(uriComponentsBuilder, "users", users.getId());

        return ResponseEntity.created(uri).body(usersDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,  @RequestBody @Valid UsersFormDTO usersFormDTO) {
        Users users = usersService.getOrThrowException(id);

        usersService.update(users, usersFormDTO);
        usersService.save(users);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Users users = usersService.getOrThrowException(id);

        usersService.delete(users);

        return ResponseEntity.noContent().build();
    }
}
