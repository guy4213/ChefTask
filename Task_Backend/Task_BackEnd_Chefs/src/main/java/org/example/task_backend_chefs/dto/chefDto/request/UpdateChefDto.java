package org.example.task_backend_chefs.dto.chefDto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UpdateChefDto  {

    private String name;
    private String description;
    private String img;
    private String food_Kosher;
    private int numOfOrders;

    //country,city: Israel,Holon.
    private String residence;
    private String deliveryCost;



}
