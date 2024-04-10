package org.example.task_backend_chefs.dto.dishDto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDishDto {

    private String name;
    private String description;
    private int price;



}
