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
public class ChefRateResponseDto {
    private Long id;
    private String name;
    private RateAvg rateAvg;
    private float averageRate;

}
