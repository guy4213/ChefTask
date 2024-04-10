package org.example.task_backend_chefs.dto.kitchenKindDto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteKitchenKindResponseDto {
    private boolean deleted;
    private KitchenKindResponseDto kitchenKindResponseDto;
}
