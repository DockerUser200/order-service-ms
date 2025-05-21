package com.codedecode.order.controller;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void saveOrder_shouldReturnCreatedStatus() {
        // Arrange
        OrderDTOFromFE orderDetails = new OrderDTOFromFE();
        OrderDTO orderSavedInDB = new OrderDTO();
        when(orderService.saveOrderInDb(orderDetails)).thenReturn(orderSavedInDB);

        // Act
        ResponseEntity<OrderDTO> response = orderController.saveOrder(orderDetails);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderSavedInDB, response.getBody());
        verify(orderService, times(1)).saveOrderInDb(orderDetails);
    }
}
