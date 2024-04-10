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
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //AUTO_INCREMENT
    private Long id;

    @NotNull
    private String name;


    private String description;
    @NotNull
    private int price;


}
