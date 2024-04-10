package org.example.task_backend_chefs.dto.RateAvgDto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class RequestRateDto {
    @NotEmpty
    private float sum=0;
    //not need to fill in numOfRates,AverageRate
    private float numOfRates=0;

    private float AverageRate=0;
}
