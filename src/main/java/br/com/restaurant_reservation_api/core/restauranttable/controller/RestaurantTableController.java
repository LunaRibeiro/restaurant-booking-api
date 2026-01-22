package br.com.restaurant_reservation_api.core.restauranttable.controller;

import br.com.restaurant_reservation_api.core.restauranttable.domain.dto.request.RestaurantTableFilterDTO;
import br.com.restaurant_reservation_api.core.restauranttable.domain.dto.response.RestaurantTableDTO;
import br.com.restaurant_reservation_api.core.restauranttable.domain.entity.RestaurantTable;
import br.com.restaurant_reservation_api.core.restauranttable.service.RestaurantTableService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant-table")
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    @GetMapping
    public ResponseEntity<Page<RestaurantTable>> listPaged(RestaurantTableFilterDTO restaurantTableFilterDTO, Pageable pageable) {
        Page<RestaurantTable> restaurantTablePage = restaurantTableService.list(restaurantTableFilterDTO, pageable);
        Page<RestaurantTableDTO> restaurantTableDTOPage = restaurantTableService.generateRestaurantTableDTOPage(restaurantTablePage);

        return ResponseEntity.ok(restaurantTableDTOPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantTableDTO>> list(RestaurantTableFilterDTO restaurantTableFilterDTO){
        List<RestaurantTable> restaurantTableList = restaurantTableService.list(restaurantTableFilterDTO);
        List<RestaurantTableDTO> restaurantTableDTOList = restaurantTableService.generateRestaurantTableDTOList(restaurantTableList);
        return ResponseEntity.ok(restaurantTableDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableDTO> get(@PathVariable Long id){
        RestaurantTable restaurantTable = restaurantTableService.getOrNull(id);
        if (restaurantTable == null){
            return ResponseEntity.notFound().build();
        }

        RestaurantTableDTO restaurantTableDTO = restaurantTableService.generateRestaurantTableDTO(restaurantTable);

        return ResponseEntity.ok(restaurantTableDTO);
    }



}
