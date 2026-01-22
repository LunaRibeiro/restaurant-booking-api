package br.com.restaurant_reservation_api.core.restauranttables.service;

import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import br.com.restaurant_reservation_api.common.specification.SpecificationHelper;
import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request.RestaurantTablesFilterDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.request.RestaurantTablesFormDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.dto.response.RestaurantTablesDTO;
import br.com.restaurant_reservation_api.core.restauranttables.domain.entity.RestaurantTables;
import br.com.restaurant_reservation_api.core.restauranttables.repository.RestaurantTablesRepository;
import br.com.restaurant_reservation_api.core.restauranttables.mapper.RestaurantTablesCreateMapper;
import br.com.restaurant_reservation_api.core.restauranttables.mapper.RestaurantTablesDTOMapper;
import br.com.restaurant_reservation_api.core.restauranttables.mapper.RestaurantTablesUpdateMapper;
import br.com.restaurant_reservation_api.core.restauranttables.specification.RestaurantTablesSpecification;
import br.com.restaurant_reservation_api.core.status.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantTablesService {

    private final RestaurantTablesRepository restaurantTablesRepository;
    private final RestaurantTablesCreateMapper restaurantTablesCreateMapper;
    private final RestaurantTablesDTOMapper restaurantTablesDTOMapper;
    private final RestaurantTablesUpdateMapper restaurantTablesUpdateMapper;


    public RestaurantTables save(RestaurantTables restaurantTables) {
        return restaurantTablesRepository.save(restaurantTables);
    }

    public RestaurantTables generateRestaurantTable(RestaurantTablesFormDTO restaurantTablesFormDTO){
        return restaurantTablesCreateMapper.convert(restaurantTablesFormDTO);
    }

    public RestaurantTablesDTO generateRestaurantTableDTO(RestaurantTables restaurantTables){
        return restaurantTablesDTOMapper.convert(restaurantTables);
    }

    public void update(RestaurantTables restaurantTables, RestaurantTablesFormDTO restaurantTablesFormDTO) {
        restaurantTablesUpdateMapper.update(restaurantTables, restaurantTablesFormDTO);
    }

    public void delete(RestaurantTables restaurantTables) {
        restaurantTablesRepository.delete(restaurantTables);
    }

    public RestaurantTables getOrNull(Long id){
        if (id == null) { return null; }
        return restaurantTablesRepository.findById(id).orElse(null);
    }

    public List<RestaurantTables> list(RestaurantTablesFilterDTO restaurantTablesFilterDTO){
        Specification<RestaurantTables> restaurantTableSpecification = generateSpecification(restaurantTablesFilterDTO);
        return restaurantTablesRepository.findAll(restaurantTableSpecification);
    }

    public Page<RestaurantTables> list(RestaurantTablesFilterDTO restaurantTablesFilterDTO, Pageable pageable){
        Specification<RestaurantTables> restaurantTableSpecification = generateSpecification(restaurantTablesFilterDTO);
        return restaurantTablesRepository.findAll(restaurantTableSpecification, pageable);
    }

    private Specification<RestaurantTables> generateSpecification(RestaurantTablesFilterDTO restaurantTablesFilterDTO){
        SearchCriteria<Integer> tableNumberCriteria = SpecificationHelper.generateEqualsCriteria("tableNumber", restaurantTablesFilterDTO.tableNumber());
        SearchCriteria<Integer> capacityCriteria =  SpecificationHelper.generateEqualsCriteria("capacity", restaurantTablesFilterDTO.capacity());
        SearchCriteria<Status> statusCriteria =  SpecificationHelper.generateEqualsCriteria("status", restaurantTablesFilterDTO.status());

        RestaurantTablesSpecification tableNumberSpecification = new RestaurantTablesSpecification(tableNumberCriteria);
        RestaurantTablesSpecification capacitySpecification = new RestaurantTablesSpecification(capacityCriteria);
        RestaurantTablesSpecification statusSpecification = new RestaurantTablesSpecification(statusCriteria);

        return Specification.where(tableNumberSpecification)
                .and(capacitySpecification)
                .and(statusSpecification);
    }

    public Page<RestaurantTablesDTO> generateRestaurantTableDTOPage(Page<RestaurantTables> restaurantTablesPage) {
        return restaurantTablesPage.map(this::generateRestaurantTableDTO);
    }

    public List<RestaurantTablesDTO> generateRestaurantTableDTOList(List<RestaurantTables> restaurantTablesList){
        return restaurantTablesList.stream().map(restaurantTablesDTOMapper::convert).toList();
    }

    public RestaurantTables getOrThrowException(Long id){
        return restaurantTablesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("RestaurantTable")
        );
    }
}
