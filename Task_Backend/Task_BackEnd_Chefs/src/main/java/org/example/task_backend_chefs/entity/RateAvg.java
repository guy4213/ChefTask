package org.example.task_backend_chefs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RateAvg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //AUTO_INCREMENT
    private Long id;


    private float sum=0;
    //not need to fill in numOfRates,AverageRate

    private int numOfRates=0;
}
