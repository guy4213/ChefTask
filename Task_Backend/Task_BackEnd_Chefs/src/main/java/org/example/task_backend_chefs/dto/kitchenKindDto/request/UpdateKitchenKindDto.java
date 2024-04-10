package org.example.task_backend_chefs.dto.kitchenKindDto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateKitchenKindDto {

    @NotNull
    @Size(min = 1, max = 30)
    private String name;
    @NotNull
    @Size(min = 2, max = 255)
    private String description;

}
