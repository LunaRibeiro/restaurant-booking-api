package br.com.restaurant_reservation_api.core.users.controller;

import br.com.restaurant_reservation_api.common.utils.HttpUtils;
import br.com.restaurant_reservation_api.core.users.domain.dto.request.UsersFormDTO;
import br.com.restaurant_reservation_api.core.users.domain.dto.response.UsersDTO;
import br.com.restaurant_reservation_api.core.users.domain.entity.Users;
import br.com.restaurant_reservation_api.core.users.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersDTO> create(@RequestBody @Valid UsersFormDTO usersFormDTO, UriComponentsBuilder uriComponentsBuilder) {

        Users users = usersService.generateUsers(usersFormDTO);
        usersService.save(users);

        UsersDTO usersDTO = usersService.generateUsersDTO(users);

        URI uri = HttpUtils.createURI(uriComponentsBuilder, "users", users.getId());

        return ResponseEntity.created(uri).body(usersDTO);
    }

}
