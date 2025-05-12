package com.codedecode.order.service;

import com.codedecode.order.dto.FoodItemsDTO;
import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.dto.UserDTO;
import com.codedecode.order.entity.Order;
import com.codedecode.order.mapper.OrderMapper;
import com.codedecode.order.repositorty.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    OrderRepository  orderRepository;

    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
        int newOrderId = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO);
        orderRepository.save(orderToBeSaved);
        return OrderMapper.INSTANCE.orderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(int userId) {

        return restTemplate.getForObject("http://USERINFORMATION/user/fetchUserById/" + userId, UserDTO.class);
    }
}
