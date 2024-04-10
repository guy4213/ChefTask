package org.example.task_backend_chefs.dto.chefDto.request;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.task_backend_chefs.entity.RateAvg;

@Data
@NoArgsConstructor
public class ChefRequestDto {

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Size(min = 2, max = 255)
    private String description;

    @Lob
    @NotNull
    private String img;




    //kosher / not kosher
    @NotNull
    private String food_Kosher;



    //country,city: Israel,Holon.
    @NotNull
    @Size(min = 2, max = 255)
    private String residence;

    //  orders number.
    @NotNull
    private int numOfOrders;

    @NotNull
    private String deliveryCost;

}
