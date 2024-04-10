package org.example.task_backend_chefs.dto.chefDto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChefDeleteResponseDto {
    private boolean deleted;
    private ChefResponseDto chefResponseDto;
}
