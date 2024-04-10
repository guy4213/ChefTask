package org.example.task_backend_chefs.dto.dishDto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteDishResponseDto {
    private boolean deleted;
    private DishResponseDto dishResponseDto;
}
