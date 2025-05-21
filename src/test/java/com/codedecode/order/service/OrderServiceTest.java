package com.codedecode.order.service;

import com.codedecode.order.dto.*;
import com.codedecode.order.entity.Order;
import com.codedecode.order.mapper.OrderMapper;
import com.codedecode.order.repositorty.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepo;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void saveOrderInDb_shouldSaveOrderAndReturnOrderDTO() {
        // Arrange
        List<FoodItemsDTO> mockedFoodItemsList = Arrays.asList(new FoodItemsDTO(1, "Item1", "ItemDescription1", true, 200, 1, 1),
                new FoodItemsDTO(2, "Item2", "ItemDescription2", true, 400, 1, 1));

        RestaurantDTO mokedRestaurantDTO = new RestaurantDTO(1, "Restaurant1", "Address1", "CITY1", "RestaurantDescription1") ;

        OrderDTOFromFE orderDetails = new OrderDTOFromFE(mockedFoodItemsList, 1, mokedRestaurantDTO);

        Integer newOrderId = 1;
        UserDTO userDTO = new UserDTO();
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO);
        OrderDTO orderDTOExpected = OrderMapper.INSTANCE.orderToOrderDTO(orderToBeSaved);

        when(sequenceGenerator.generateNextOrderId()).thenReturn(newOrderId);
        when(restTemplate.getForObject(anyString(), eq(UserDTO.class))).thenReturn(userDTO);
        when(orderRepo.save(orderToBeSaved)).thenReturn(orderToBeSaved);

        // Act
        OrderDTO orderDTOActual = orderService.saveOrderInDb(orderDetails);

        // Assert

        assertDoesNotThrow(() -> orderService.saveOrderInDb(orderDetails));
        verify(sequenceGenerator, times(1)).generateNextOrderId();

    }
}
