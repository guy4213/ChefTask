package org.example.task_backend_chefs.service;

import lombok.RequiredArgsConstructor;
import org.example.task_backend_chefs.dto.kitchenKindDto.request.KitchenRequestDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.request.UpdateKitchenKindDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.response.DeleteKitchenKindResponseDto;
import org.example.task_backend_chefs.dto.kitchenKindDto.response.KitchenKindResponseDto;
import org.example.task_backend_chefs.entity.Kitchen_Kind;
import org.example.task_backend_chefs.error.ResourceNotFoundException;
import org.example.task_backend_chefs.repository.KitchenKindRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
public class KitchenKindServiceImpl implements KitchenKindService {
    private final KitchenKindRepository kitchenKindRepository;
    private final ModelMapper modelMapper;
   @Override
   public KitchenKindResponseDto addKitchenKind (KitchenRequestDto dto) {
        var kitchenKindEntity=modelMapper.map(dto, Kitchen_Kind.class);
        //Series->saved Series
        var saved= kitchenKindRepository.save(kitchenKindEntity);
        //saved Series->Response Dto
        return modelMapper.map(saved, KitchenKindResponseDto.class);
    }

    @Override
    public  Collection<KitchenKindResponseDto> getAll(){
            var all = kitchenKindRepository.findAll();
            return all.stream().map(m -> modelMapper.map(m, KitchenKindResponseDto.class)).toList();
        }

    @Override
    public DeleteKitchenKindResponseDto deleteKitchenKindById(long id){
        var kitchenKind = kitchenKindRepository.findById(id);

        //delete:
        kitchenKindRepository.deleteById(id);

        //return true if existed before deletion:
        return DeleteKitchenKindResponseDto.builder()
                .deleted(kitchenKind.isPresent()).kitchenKindResponseDto(modelMapper.map(kitchenKind, KitchenKindResponseDto.class)).build();
    }

    @Override
    public KitchenKindResponseDto update(long id, UpdateKitchenKindDto req){
        Kitchen_Kind kitchenKind=getKitchenKindByIdOrThrow(id);

        //update:
        kitchenKind.setName(req.getName());
        kitchenKind.setDescription(req.getDescription());


        var saved = kitchenKindRepository.save(kitchenKind);
        return modelMapper.map(saved, KitchenKindResponseDto.class);
    }


    @Override
    public KitchenKindResponseDto getKitchenKindById(long id){
        Kitchen_Kind kitchenKind=getKitchenKindByIdOrThrow(id);
        var saved = kitchenKindRepository.save(kitchenKind);
        return modelMapper.map(saved,KitchenKindResponseDto.class);
    }

    @Override
    public List<KitchenKindResponseDto> search(String name){
        var ContainsList =  kitchenKindRepository.findAll().stream().filter
                (kitchenKind ->  kitchenKind.getName().toLowerCase().contains(name.toLowerCase()));
        return ContainsList.map(kitchenKind -> modelMapper.map(kitchenKind, KitchenKindResponseDto.class)).toList();

    }
    @Override

    public Kitchen_Kind getKitchenKindByIdOrThrow(long id) {
        return kitchenKindRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Kitchen_Kind", "id", id)
        );
    }

}
