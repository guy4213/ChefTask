package org.example.task_backend_chefs.repository;

import org.example.task_backend_chefs.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository extends JpaRepository<Chef,Long >  {
}
