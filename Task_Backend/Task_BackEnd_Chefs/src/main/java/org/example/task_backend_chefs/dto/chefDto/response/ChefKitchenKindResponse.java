package org.example.task_backend_chefs.dto.chefDto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.task_backend_chefs.entity.Dish;
import org.example.task_backend_chefs.entity.Kitchen_Kind;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChefKitchenKindResponse {
    private Long id;
    private String name;
    private float averageRate;
    private float AvgPrice;

    private Set<Dish> available_dishes;
    private Set<Kitchen_Kind>  kitchen_kinds;
}
