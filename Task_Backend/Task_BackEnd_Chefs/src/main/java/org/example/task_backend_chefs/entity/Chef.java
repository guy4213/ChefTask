package org.example.task_backend_chefs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //AUTO_INCREMENT
    private Long id;

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Size(min = 2, max = 255)
    private String description;

    @Lob
    @NotNull
    private String img;


    //1-10
//rate comp for calculating
    @OneToOne(cascade = CascadeType.ALL)
    private RateAvg rateAvg=new RateAvg();

    private float averageRate;
    //kosher / not kosher
    @NotNull
    private String food_Kosher;

    private float sumOfDishesPrices;

    private float avgPrice;

    //country,city: Israel,Holon.
    @NotNull
    @Size(min = 2, max = 255)
    private String residence;

    //  orders number.
    @NotNull
    private int numOfOrders;

    @NotNull
    private String deliveryCost;


    //needs to be equal to the kitchen kind dishes.
    @ManyToMany (fetch = FetchType.EAGER,cascade = CascadeType.ALL)

    private Set<Dish>  available_dishes;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)

    private Set<Kitchen_Kind>  kitchen_kinds;












}
