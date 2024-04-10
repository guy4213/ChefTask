package org.example.task_backend_chefs.repository;

import org.example.task_backend_chefs.entity.Kitchen_Kind;
import org.example.task_backend_chefs.entity.RateAvg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateAvgRepository extends JpaRepository<RateAvg,Long > {
}
