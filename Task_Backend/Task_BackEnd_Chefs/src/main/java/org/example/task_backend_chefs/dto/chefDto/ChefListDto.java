package org.example.task_backend_chefs.dto.chefDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.task_backend_chefs.dto.chefDto.response.ChefResponseDto;

import java.util.Collection;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChefListDto {
//pagination:
    private long totalChefs;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;

    private final Collection<ChefResponseDto> Chefs;
}
