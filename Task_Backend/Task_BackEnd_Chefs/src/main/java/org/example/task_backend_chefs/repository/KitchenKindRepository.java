package org.example.task_backend_chefs.repository;

import org.example.task_backend_chefs.entity.Dish;
import org.example.task_backend_chefs.entity.Kitchen_Kind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenKindRepository extends JpaRepository<Kitchen_Kind,Long >  {
    Kitchen_Kind findKitchenKindByNameIgnoreCase(String kitchenKindName);

}
