package org.example.task_backend_chefs.service;

import org.example.task_backend_chefs.dto.chefDto.response.ChefResponseDto;
import org.example.task_backend_chefs.dto.dishDto.request.DishRequestDto;
import org.example.task_backend_chefs.dto.dishDto.request.UpdateDishDto;
import org.example.task_backend_chefs.dto.dishDto.response.DeleteDishResponseDto;
import org.example.task_backend_chefs.dto.dishDto.response.DishResponseDto;

import java.util.Collection;
import java.util.List;

public interface DishService {
   DishResponseDto addDish (DishRequestDto dto);

    Collection<DishResponseDto> getAll();
    DeleteDishResponseDto deleteDishById(long id) ;
   DishResponseDto update(long id, UpdateDishDto req) ;

    DishResponseDto getDishById(long id);

    List<DishResponseDto> search(String name);


}
