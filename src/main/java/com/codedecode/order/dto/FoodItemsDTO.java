package com.codedecode.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemsDTO {

    private int id;
    private String itemName;
    private String itemDescription;
    private boolean veg;
    private Integer price;
    private Integer restaurantId;
    private Integer quantity;
}
