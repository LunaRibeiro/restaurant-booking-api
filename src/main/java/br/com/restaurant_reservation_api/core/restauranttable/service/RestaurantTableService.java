package br.com.restaurant_reservation_api.core.restauranttable.service;

import br.com.restaurant_reservation_api.common.specification.SearchCriteria;
import br.com.restaurant_reservation_api.common.specification.SpecificationHelper;
import br.com.restaurant_reservation_api.core.restauranttable.domain.dto.request.RestaurantTableFilterDTO;
import br.com.restaurant_reservation_api.core.restauranttable.domain.dto.request.RestaurantTableFormDTO;
import br.com.restaurant_reservation_api.core.restauranttable.domain.dto.response.RestaurantTableDTO;
import br.com.restaurant_reservation_api.core.restauranttable.domain.entity.RestaurantTable;
import br.com.restaurant_reservation_api.core.restauranttable.repository.RestaurantTableRepository;
import br.com.restaurant_reservation_api.core.restauranttable.mapper.RestaurantTableCreateMapper;
import br.com.restaurant_reservation_api.core.restauranttable.mapper.RestaurantTableDTOMapper;
import br.com.restaurant_reservation_api.core.restauranttable.mapper.RestaurantTableUpdateMapper;
import br.com.restaurant_reservation_api.core.restauranttable.specification.RestaurantTableSpecification;
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
public class RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantTableCreateMapper restaurantTableCreateMapper;
    private final RestaurantTableDTOMapper restaurantTableDTOMapper;
    private final RestaurantTableUpdateMapper restaurantTableUpdateMapper;


    public RestaurantTable save(RestaurantTable restaurantTable) {
        return restaurantTableRepository.save(restaurantTable);
    }

    public RestaurantTable generateRestaurantTable(RestaurantTableFormDTO restaurantTableFormDTO){
        return restaurantTableCreateMapper.convert(restaurantTableFormDTO);
    }

    public RestaurantTableDTO generateRestaurantTableDTO(RestaurantTable restaurantTable){
        return restaurantTableDTOMapper.convert(restaurantTable);
    }

    public void update(RestaurantTable restaurantTable, RestaurantTableFormDTO restaurantTableFormDTO) {
        restaurantTableUpdateMapper.update(restaurantTable, restaurantTableFormDTO);
    }

    public void delete(RestaurantTable restaurantTable) {
        restaurantTableRepository.delete(restaurantTable);
    }

    public RestaurantTable getOrNull(Long id){
        if (id == null) { return null; }
        return restaurantTableRepository.findById(id).orElse(null);
    }

    public List<RestaurantTable> list(RestaurantTableFilterDTO restaurantTableFilterDTO){
        Specification<RestaurantTable> restaurantTableSpecification = generateSpecification(restaurantTableFilterDTO);
        return restaurantTableRepository.findAll(restaurantTableSpecification);
    }

    public Page<RestaurantTable> list(RestaurantTableFilterDTO restaurantTableFilterDTO, Pageable pageable){
        Specification<RestaurantTable> restaurantTableSpecification = generateSpecification(restaurantTableFilterDTO);
        return restaurantTableRepository.findAll(restaurantTableSpecification, pageable);
    }

    private Specification<RestaurantTable> generateSpecification(RestaurantTableFilterDTO restaurantTableFilterDTO){
        SearchCriteria<Integer> tableNumberCriteria = SpecificationHelper.generateEqualsCriteria("tableNumber", restaurantTableFilterDTO.tableNumber());
        SearchCriteria<Integer> capacityCriteria =  SpecificationHelper.generateEqualsCriteria("capacity", restaurantTableFilterDTO.capacity());
        SearchCriteria<Status> statusCriteria =  SpecificationHelper.generateEqualsCriteria("status", restaurantTableFilterDTO.status());

        RestaurantTableSpecification tableNumberSpecification = new RestaurantTableSpecification(tableNumberCriteria);
        RestaurantTableSpecification capacitySpecification = new RestaurantTableSpecification(capacityCriteria);
        RestaurantTableSpecification statusSpecification = new RestaurantTableSpecification(statusCriteria);

        return Specification.where(tableNumberSpecification)
                .and(capacitySpecification)
                .and(statusSpecification);
    }

    public Page<RestaurantTableDTO> generateRestaurantTableDTOPage(Page<RestaurantTable> restaurantTablesPage) {
        return restaurantTablesPage.map(this::generateRestaurantTableDTO);
    }

    public List<RestaurantTableDTO> generateRestaurantTableDTOList(List<RestaurantTable> restaurantTableList){
        return restaurantTableList.stream().map(restaurantTableDTOMapper::convert).toList();
    }

    public RestaurantTable getOrThrowException(Long id){
        return restaurantTableRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("RestaurantTable")
        );
    }
}
