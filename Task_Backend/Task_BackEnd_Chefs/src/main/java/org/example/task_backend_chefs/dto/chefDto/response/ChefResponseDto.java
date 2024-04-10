package org.example.task_backend_chefs.dto.chefDto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.task_backend_chefs.entity.Dish;
import org.example.task_backend_chefs.entity.Kitchen_Kind;
import org.example.task_backend_chefs.entity.RateAvg;

import java.util.Set;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChefResponseDto {
    private Long id;


    private String name;


    private String description;


    private String img;

    //1-10


    private float averageRate;
    //kosher / not kosher

    private String food_Kosher;


    private float AvgPrice;

    //country,city: Israel,Holon.

    private String residence;

    //  orders number.

    private int numOfOrders;


    private String deliveryCost;

    //needs to be equal to the kitchen kind dishes.
    private RateAvg rateAvg;

    private Set<Dish> available_dishes;


    private Set<Kitchen_Kind>  kitchen_kinds;


}
