package org.example.task_backend_chefs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.task_backend_chefs.dto.chefDto.ChefListDto;
import org.example.task_backend_chefs.dto.chefDto.request.ChefRequestDto;
import org.example.task_backend_chefs.dto.chefDto.request.UpdateChefDto;
import org.example.task_backend_chefs.dto.chefDto.response.*;
import org.example.task_backend_chefs.service.ChefServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:5173","http://localhost:3000"})
@RequestMapping("/api/v1/Chef")
public class ChefController {
    private final ChefServiceImpl chefService;

    //rate chef method         todo: Replacing Exception error w out of range error.
    //todo: * if we want to sort by param in obj inside chef entity:
    // todo: * we must create a primitive param that gets the specific value.
    // todo:search by:name V, KitchenKind  HARD V, residence Easy V , By Dishes(names) HARD V

    @PostMapping("/addRate")
    public ResponseEntity<ChefRateResponseDto> AddRate(@RequestParam(value = "ChefID", required = true,
            defaultValue = "id") long ChefID,
                                                       @RequestParam(value = "rate", required = true,
                                                           defaultValue = "0") float rate) throws Exception {

            var res = chefService.addRate(ChefID, rate);
            System.out.println("response DTO= " + res.getAverageRate());
            return ResponseEntity.ok(res);



    }

    @PostMapping
    public ResponseEntity<ChefSimpleResponseDto> addChef(@Valid @RequestBody
                                                   ChefRequestDto dto) {
        var res = chefService.addChef(dto);
        var id = res.getId();
        var uri = URI.create("/Chef/%d".formatted(id));
        return ResponseEntity.created(uri).body(res);
    }

    @PostMapping("/addDishesByNames")
    public ResponseEntity<ChefKitchenKindResponse> addDishes(
            @RequestParam(value = "ChefId", required = true,
                    defaultValue = "id") long ChefId,
            @RequestParam(value = "dishNames", required = true,
                    defaultValue = "name") String... dishNames) {
        var res = chefService.addDishes(ChefId, dishNames);
        System.out.println("response DTO= " + res.getAvailable_dishes());

        return ResponseEntity.ok(res);
    }
    @PostMapping("/addKitchenKindsByNames")
    public ResponseEntity<ChefKitchenKindResponse> addKitchenKind(
            @RequestParam(value = "ChefId", required = true,
                    defaultValue = "id") long ChefId,
            @RequestParam(value = "kitchenKindName", required = true,
                    defaultValue = "name") String ... kitchenKindNames)

    {
        var res = chefService.addKitchenKind(ChefId, kitchenKindNames);
        System.out.println("response DTO= " + res.getKitchen_kinds());

        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<ChefListDto> getAll
            (@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
             @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
             @RequestParam(value = "sortBy", required = false, defaultValue = "id") String... sortBy
            ) {
        var res = chefService.getAll(pageNo, pageSize, sortDir, sortBy);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChefDeleteResponseDto> deleteChefById(@PathVariable long id) {
        //check for existence before deleting:
        return ResponseEntity.ok(chefService.deleteChefById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ChefResponseDto> update(
            @PathVariable long id,
            @Valid @RequestBody UpdateChefDto req) {
        return ResponseEntity.ok(chefService.update(id, req));


    }

    @GetMapping("/{id}")
    public ResponseEntity<ChefResponseDto> getChefById(@PathVariable long id) {
        return ResponseEntity.ok(chefService.getChefById(id));

    }


    //search functions
    @GetMapping("search/{name}")
    public ResponseEntity<ChefListDto> searchByName(@PathVariable String name) {

        var res = chefService.searchByName(name);
        var list = new ChefListDto(res);
        return ResponseEntity.ok(list);
    }

    @GetMapping("searchResidence/{residenceName}")
    public ResponseEntity<ChefListDto>searchResidence(@PathVariable String residenceName) {

        var res = chefService.searchByResidence(residenceName);
        var list = new ChefListDto(res);
        return ResponseEntity.ok(list);
    }
    @GetMapping("searchKitchenKindName/{kitchenKindName}")
    public ResponseEntity<ChefListDto> searchKitchenKindName(@PathVariable String kitchenKindName){
        var res = chefService.searchKitchenKindName(kitchenKindName);
        var list = new ChefListDto(res);
        return ResponseEntity.ok(list);
    }
    @GetMapping("searchByDishName/{dishName}")
    public ResponseEntity<ChefListDto>  searchByDishName(@PathVariable String dishName){
        var res = chefService.searchByDishName(dishName);
        var list = new ChefListDto(res);
        return ResponseEntity.ok(list);
    }

//    @GetMapping("search/{residenceName}")
//    public ResponseEntity<ChefListDto> searchResidence(@PathVariable String residenceName) {
//
//        var res = chefService.searchByResidence(residenceName);
//        var list = new ChefListDto(res);
//        return ResponseEntity.ok(list);
//    }
}
