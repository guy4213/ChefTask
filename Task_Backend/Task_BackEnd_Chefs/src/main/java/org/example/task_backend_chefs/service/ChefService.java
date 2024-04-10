package org.example.task_backend_chefs.service;

import org.example.task_backend_chefs.dto.chefDto.ChefListDto;
import org.example.task_backend_chefs.dto.chefDto.request.ChefRequestDto;
import org.example.task_backend_chefs.dto.chefDto.request.UpdateChefDto;
import org.example.task_backend_chefs.dto.chefDto.response.ChefRateResponseDto;
import org.example.task_backend_chefs.dto.chefDto.response.ChefResponseDto;
import org.example.task_backend_chefs.dto.chefDto.response.ChefDeleteResponseDto;
import org.example.task_backend_chefs.dto.chefDto.response.ChefSimpleResponseDto;

import java.util.List;

public interface ChefService {
    ChefSimpleResponseDto addChef (ChefRequestDto dto);
    ChefRateResponseDto addRate(long ChefID, float rate) throws Exception;
    ChefListDto getAll(int pageNo, int pageSize, String sortDir, String... sortBy) ;
    ChefDeleteResponseDto deleteChefById(long id) ;

    ChefResponseDto update(long id, UpdateChefDto req) ;
     ChefResponseDto getChefById( long id);
    List<ChefResponseDto> searchByName(String name);
    List<ChefResponseDto> searchByResidence(String residenceName);

}
