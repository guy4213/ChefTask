package org.example.task_backend_chefs.service;

import org.example.task_backend_chefs.dto.kitchenKindDto.request.KitchenRequestDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.request.UpdateKitchenKindDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.response.DeleteKitchenKindResponseDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.response.KitchenKindResponseDto;
import org.example.task_backend_chefs.entity.Kitchen_Kind;

import java.util.Collection;
import java.util.List;

public interface KitchenKindService {
    KitchenKindResponseDto addKitchenKind (KitchenRequestDto dto);

    Collection<KitchenKindResponseDto> getAll();
    DeleteKitchenKindResponseDto deleteKitchenKindById(long id) ;
    KitchenKindResponseDto update(long id, UpdateKitchenKindDto req) ;

    KitchenKindResponseDto getKitchenKindById(long id);

    List<KitchenKindResponseDto> search(String name);
    public Kitchen_Kind getKitchenKindByIdOrThrow(long id) ;

}
