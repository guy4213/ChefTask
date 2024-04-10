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
public class Kitchen_Kind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //AUTO_INCREMENT
    private Long id;


    private String name;


    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Dish> dishes;
}
