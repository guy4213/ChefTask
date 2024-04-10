package org.example.task_backend_chefs.repository;

import org.example.task_backend_chefs.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository  extends JpaRepository<Dish,Long > {
    Dish findDishByNameIgnoreCase(String dishName);

}
