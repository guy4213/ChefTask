package org.example.task_backend_chefs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.task_backend_chefs.dto.dishDto.request.DishRequestDto;
import org.example.task_backend_chefs.dto.dishDto.request.UpdateDishDto;
import org.example.task_backend_chefs.dto.dishDto.response.DeleteDishResponseDto;
import org.example.task_backend_chefs.dto.dishDto.response.DishResponseDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.request.KitchenRequestDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.request.UpdateKitchenKindDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.response.DeleteKitchenKindResponseDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.response.KitchenKindResponseDto;
import org.example.task_backend_chefs.service.DishServiceImpl;
import org.example.task_backend_chefs.service.KitchenKindServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;




//todo:add dishes to kitchenkind
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/KitchenKind")
@CrossOrigin(origins={ "http://localhost:5173","http://localhost:3000"})
public class KitchenKindController {
    private final KitchenKindServiceImpl kitchenKindService;

    @PostMapping
    public ResponseEntity<KitchenKindResponseDto> addKitchenKind (@Valid @RequestBody
                                                           KitchenRequestDto dto){
        var res =  kitchenKindService.addKitchenKind(dto);
        var id = res.getId();
        var uri = URI.create("/KitchenKind/%d".formatted(id));
        return ResponseEntity.created(uri).body(res);
    }


    @GetMapping
    public ResponseEntity<Collection<KitchenKindResponseDto>> getAll( ) {
        var res = kitchenKindService.getAll();

        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteKitchenKindResponseDto> deleteKitchenKindById(@PathVariable long id) {
        //check for existence before deleting:
        return ResponseEntity.ok(kitchenKindService.deleteKitchenKindById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<KitchenKindResponseDto> update(
            @PathVariable  long id,
            @Valid @RequestBody  UpdateKitchenKindDto req)   {
        return ResponseEntity.ok(kitchenKindService.update(id, req));


    }
    @GetMapping("/{id}")
    public ResponseEntity<KitchenKindResponseDto> getKitchenKindById(@PathVariable long id) {
        return ResponseEntity.ok(kitchenKindService.getKitchenKindById(id));

    }
    @GetMapping("search/{name}")
    public ResponseEntity<List<KitchenKindResponseDto>> search(@PathVariable String name) {

        var res = kitchenKindService.search(name);
        return ResponseEntity.ok(res);
    }
}

