package br.com.restaurant_reservation_api.core.restauranttables.controller;

import br.com.restaurant_reservation_api.common.utils.HttpUtils;
import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request.RestaurantTablesFilterDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request.RestaurantTablesFormDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.response.RestaurantTablesDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import br.com.restaurant_reservation_api.core.restauranttables.service.RestaurantTablesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant-table")
public class RestaurantTablesController {

    private final RestaurantTablesService restaurantTablesService;

    @GetMapping
    public ResponseEntity<Page<RestaurantTablesDTO>> listPaged(RestaurantTablesFilterDTO restaurantTablesFilterDTO, Pageable pageable) {
        Page<RestaurantTables> restaurantTablePage = restaurantTablesService.list(restaurantTablesFilterDTO, pageable);
        Page<RestaurantTablesDTO> restaurantTableDTOPage = restaurantTablesService.generateRestaurantTableDTOPage(restaurantTablePage);

        return ResponseEntity.ok(restaurantTableDTOPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantTablesDTO>> list(RestaurantTablesFilterDTO restaurantTablesFilterDTO){
        List<RestaurantTables> restaurantTablesList = restaurantTablesService.list(restaurantTablesFilterDTO);
        List<RestaurantTablesDTO> restaurantTablesDTOList = restaurantTablesService.generateRestaurantTableDTOList(restaurantTablesList);
        return ResponseEntity.ok(restaurantTablesDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTablesDTO> get(@PathVariable Long id){
        RestaurantTables restaurantTables = restaurantTablesService.getOrNull(id);
        if (restaurantTables == null){
            return ResponseEntity.notFound().build();
        }

        RestaurantTablesDTO restaurantTablesDTO = restaurantTablesService.generateRestaurantTableDTO(restaurantTables);

        return ResponseEntity.ok(restaurantTablesDTO);
    }

    @PostMapping
    public ResponseEntity<RestaurantTablesDTO> create(@RequestBody RestaurantTablesFormDTO restaurantTablesFormDTO, UriComponentsBuilder uriComponentsBuilder){
        RestaurantTables restaurantTables = restaurantTablesService.generateRestaurantTable(restaurantTablesFormDTO);
        restaurantTablesService.save(restaurantTables);

        RestaurantTablesDTO restaurantTablesDTO = restaurantTablesService.generateRestaurantTableDTO(restaurantTables);

        URI uri = HttpUtils.createURI(uriComponentsBuilder, "RestaurantTables", restaurantTables.getId());

        return ResponseEntity.created(uri).body(restaurantTablesDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody RestaurantTablesFormDTO restaurantTablesFormDTO){
        RestaurantTables restaurantTables = restaurantTablesService.getOrThrowException(id);

        restaurantTablesService.update(restaurantTables, restaurantTablesFormDTO);
        restaurantTablesService.save(restaurantTables);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        RestaurantTables restaurantTables = restaurantTablesService.getOrThrowException(id);

        restaurantTablesService.delete(restaurantTables);

        return ResponseEntity.noContent().build();
    }
}
