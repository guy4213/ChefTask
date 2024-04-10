package org.example.task_backend_chefs.dto.kitchenKindDto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.task_backend_chefs.entity.Dish;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KitchenKindResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<Dish> dishes;


}
