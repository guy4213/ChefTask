package org.example.task_backend_chefs.service;

import lombok.RequiredArgsConstructor;
import org.example.task_backend_chefs.dto.dishDto.request.DishRequestDto;
import org.example.task_backend_chefs.dto.dishDto.request.UpdateDishDto;
import org.example.task_backend_chefs.dto.dishDto.response.DeleteDishResponseDto;
import org.example.task_backend_chefs.dto.dishDto.response.DishResponseDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.response.DeleteKitchenKindResponseDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.response.KitchenKindResponseDto;
import org.example.task_backend_chefs.entity.Dish;
import org.example.task_backend_chefs.entity.Kitchen_Kind;
import org.example.task_backend_chefs.error.ResourceNotFoundException;
import org.example.task_backend_chefs.repository.DishRepository;
import org.example.task_backend_chefs.repository.KitchenKindRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService{
    private final DishRepository dishRepository;
    private final ModelMapper modelMapper;
    @Override
    public DishResponseDto addDish (DishRequestDto dto) {
       var dishEntity=modelMapper.map(dto, Dish.class);
       //Series->saved Series
       var saved= dishRepository.save(dishEntity);
       //saved Series->Response Dto
       return modelMapper.map(saved, DishResponseDto.class);
   }

    @Override
    public  Collection<DishResponseDto> getAll() {
        var all = dishRepository.findAll();
        return all.stream().map(m -> modelMapper.map(m, DishResponseDto.class)).toList();
    }
    @Override
    public  DeleteDishResponseDto deleteDishById(long id) {
       var dishEntity = dishRepository.findById(id);

       //delete:
       dishRepository.deleteById(id);

       //return true if existed before deletion:
       return DeleteDishResponseDto.builder()
               .deleted(dishEntity.isPresent()).dishResponseDto(modelMapper.map(dishEntity, DishResponseDto.class)).build();

   }
    @Override
    public DishResponseDto update(long id, UpdateDishDto req) {
       Dish dish=getDishByIdOrThrow(id);

       //update:
       dish.setName(req.getName());
       dish.setDescription(req.getDescription());


       var saved = dishRepository.save(dish);
       return modelMapper.map(saved, DishResponseDto.class);

   }

    @Override
    public  DishResponseDto getDishById(long id){
        Dish dish=getDishByIdOrThrow(id);
        var saved = dishRepository.save(dish);
        return modelMapper.map(saved,DishResponseDto.class);

    }

    @Override
    public  List<DishResponseDto> search(String name){
        var ContainsList =  dishRepository.findAll().stream().filter
                (dish ->  dish.getName().toLowerCase().contains(name.toLowerCase()));
        return ContainsList.map(dish -> modelMapper.map(dish, DishResponseDto.class)).toList();
    }
    Dish getDishByIdOrThrow(long id) {
        return dishRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Dish", "id", id)
        );
    }

}
