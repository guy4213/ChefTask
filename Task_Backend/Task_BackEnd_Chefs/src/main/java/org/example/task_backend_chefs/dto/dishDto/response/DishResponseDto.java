package org.example.task_backend_chefs.dto.dishDto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishResponseDto {
    private Long id;


    private String name;


    private String description;

    private int price;


}
