package org.example.task_backend_chefs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.task_backend_chefs.dto.dishDto.request.DishRequestDto;
import org.example.task_backend_chefs.dto.dishDto.request.UpdateDishDto;
import org.example.task_backend_chefs.dto.dishDto.response.DeleteDishResponseDto;
import org.example.task_backend_chefs.dto.dishDto.response.DishResponseDto;
import org.example.task_backend_chefs.service.DishServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Dish")
@CrossOrigin(origins={ "http://localhost:5173","http://localhost:3000"})
public class DishController {
    private final DishServiceImpl dishService;

    @PostMapping
    public ResponseEntity<DishResponseDto> addDish (@Valid @RequestBody
                                                    DishRequestDto dto){
        var res =  dishService.addDish(dto);
        var id = res.getId();
        var uri = URI.create("/Dish/%d".formatted(id));
        return ResponseEntity.created(uri).body(res);
    }


    @GetMapping
    public ResponseEntity<Collection<DishResponseDto>> getAll( ) {
        var res = dishService.getAll();

        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDishResponseDto> deleteDishById(@PathVariable long id) {
        //check for existence before deleting:
        return ResponseEntity.ok(dishService.deleteDishById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<DishResponseDto> update(
            @PathVariable  long id,
            @Valid @RequestBody UpdateDishDto req) {
        return ResponseEntity.ok(dishService.update(id, req));


    }
    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getChefById(@PathVariable long id) {
        return ResponseEntity.ok(dishService.getDishById(id));

    }
    @GetMapping("search/{name}")
    public ResponseEntity<List<DishResponseDto>> search(@PathVariable String name) {

        var res = dishService.search(name);
        return ResponseEntity.ok(res);
    }
}

