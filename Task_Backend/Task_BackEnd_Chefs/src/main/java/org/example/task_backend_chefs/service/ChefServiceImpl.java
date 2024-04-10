package org.example.task_backend_chefs.service;

import lombok.RequiredArgsConstructor;
import org.example.task_backend_chefs.dto.chefDto.ChefListDto;
import org.example.task_backend_chefs.dto.chefDto.request.ChefRequestDto;
import org.example.task_backend_chefs.dto.chefDto.request.UpdateChefDto;
import org.example.task_backend_chefs.dto.chefDto.response.*;
import org.example.task_backend_chefs.entity.Chef;
import org.example.task_backend_chefs.entity.Dish;
import org.example.task_backend_chefs.entity.Kitchen_Kind;
import org.example.task_backend_chefs.entity.RateAvg;
import org.example.task_backend_chefs.error.PaginationException;
import org.example.task_backend_chefs.error.RateOutOFRangeException;
import org.example.task_backend_chefs.error.ResourceNotFoundException;
import org.example.task_backend_chefs.repository.ChefRepository;
import org.example.task_backend_chefs.repository.DishRepository;
import org.example.task_backend_chefs.repository.KitchenKindRepository;
import org.example.task_backend_chefs.repository.RateAvgRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChefServiceImpl implements ChefService {
    private final DishRepository dishRepository;
    private final KitchenKindRepository kitchenKindRepository;
    private final ModelMapper modelMapper;
    private final ChefRepository chefRepository;
    private final RateAvgRepository rateAvgRepository;

    //rate chef method         todo: short that shit ASAP
    //rate chef method         todo: Replacing Exception error w out of range error.

    @Override
    public ChefRateResponseDto addRate(long ChefID, float rate) throws Exception {
        if (rate < 1 || rate > 10) {
            System.out.println("rate must be btw 1-10");
            throw new RateOutOFRangeException(rate);
        }
        Chef chefEntity =getChefByIdOrThrow(ChefID);
        var existedRate=chefEntity.getRateAvg();
        //transferring the data into another method for logic calculations
      var relevantRate = calculateRate(existedRate,rate);

       //setting the average rate at the param of chef entity for display purpose
            chefEntity.setAverageRate(relevantRate.getSum()/relevantRate.getNumOfRates());
            //saving the rate obj
            var rateSaved= rateAvgRepository.save(relevantRate);
            //setting it into chef entity.
            chefEntity.setRateAvg(rateSaved);


       var saved= chefRepository.save(chefEntity);
    System.out.println(saved.getAverageRate());
    return modelMapper.map(saved, ChefRateResponseDto.class);


    }
    private RateAvg calculateRate(RateAvg existedRate,float rate){
        var sum =rate;
        int numOfRates=1;
        var relevantRateComp=new RateAvg();
        if (existedRate != null) {
            relevantRateComp = existedRate;
            sum = relevantRateComp.getSum() + rate;
            numOfRates = (int) (relevantRateComp.getNumOfRates() + 1);
       }
        relevantRateComp.setNumOfRates(numOfRates);
        relevantRateComp.setSum(sum);

        return relevantRateComp;
    }
  public  void calculatePriceAvg(long id){
      Chef chef=  getChefByIdOrThrow(id);
      var currentSum= chef.getSumOfDishesPrices();
     chef.setSumOfDishesPrices((float) (currentSum + chef.getAvailable_dishes().stream()
                        .mapToDouble(Dish::getPrice)  // Map dishes to their prices
                        .sum()));  // Sum up the prices
        chef.setAvgPrice(chef.getSumOfDishesPrices()/chef.getAvailable_dishes().size());

    }



    @Override
   public ChefSimpleResponseDto addChef (ChefRequestDto dto){
        //request dto=> Series
        var chefEntity=modelMapper.map(dto, Chef.class);
        //Series->saved Series
        var saved= chefRepository.save(chefEntity);
        //saved Series->Response Dto
        return modelMapper.map(saved, ChefSimpleResponseDto.class);

    }
    public ChefKitchenKindResponse  addDishes(long chefId, String[] dishNames){
        Chef  chefEntity= getChefByIdOrThrow(chefId);

        var dishList = Arrays.stream(dishNames)
                .map(dishRepository::findDishByNameIgnoreCase)
                .toList();
        chefEntity.getAvailable_dishes().addAll(dishList);
        calculatePriceAvg(chefId);
        var saved= chefRepository.save(chefEntity);
        System.out.println(saved.getAvailable_dishes());
        return modelMapper.map(saved,ChefKitchenKindResponse.class);
    }

//
    public ChefKitchenKindResponse addKitchenKind(long chefId, String[] kitchenKindNames){
               Chef  chefEntity= getChefByIdOrThrow(chefId);
        var kitchenKindList = Arrays.stream(kitchenKindNames)
                .map(kitchenKindRepository::findKitchenKindByNameIgnoreCase)
                .toList();
        chefEntity.getKitchen_kinds().addAll(kitchenKindList);
        var saved= chefRepository.save(chefEntity);
        System.out.println(saved.getKitchen_kinds());
        return modelMapper.map(saved,ChefKitchenKindResponse.class);
    }

    @Override
    public  ChefListDto getAll(int pageNo, int pageSize, String sortDir, String... sortBy) {
        try {
            //Direction from String ('asc/des')
            Sort.Direction sort = Sort.Direction.fromString(sortDir);
            //build the page req
            var pageable = PageRequest.of(pageNo, pageSize, sort, sortBy);

            //get the page result from the repository:
            Page<Chef> pr = chefRepository.findAll(pageable);

            if (pageNo > pr.getTotalPages()) {
                throw new PaginationException("Chef " + pageNo + " pageNo " + pr.getTotalPages());
            }
            // transferring data into the SeriesListDto
            List<ChefResponseDto> chefListDto =
                    pr.getContent().stream()
                            .map(p -> modelMapper.map(p, ChefResponseDto.class))
                            .toList();

            return new ChefListDto(
                    pr.getTotalElements(),
                    pr.getNumber(),
                    pr.getSize(),
                    pr.getTotalPages(),
                    pr.isFirst(),
                    pr.isLast(),
                    chefListDto
            );
        }catch (IllegalArgumentException e){
            throw new PaginationException((e.getMessage()));

        }
    }
    @Override
   public ChefDeleteResponseDto deleteChefById(long id) {
         //check for existence before deleting:
         var chef = chefRepository.findById(id);

         //delete:
         chefRepository.deleteById(id);

         //return true if existed before deletion:
         return ChefDeleteResponseDto.builder()
                 .deleted(chef.isPresent()).chefResponseDto(modelMapper.map(chef, ChefResponseDto.class)).build();
     }

    @Override
    public ChefResponseDto update(long id, UpdateChefDto req) {

        Chef chef=getChefByIdOrThrow(id);

        //update:
        chef.setName(req.getName());
        chef.setDescription(req.getDescription());
        chef.setImg(req.getImg());
        chef.setFood_Kosher(req.getFood_Kosher());
        chef.setNumOfOrders(req.getNumOfOrders());
        chef.setResidence(req.getResidence());
        chef.setDeliveryCost(req.getDeliveryCost());

        var saved = chefRepository.save(chef);
        return modelMapper.map(saved, ChefResponseDto.class);

    }
    @Override
    public ChefResponseDto getChefById( long id) {
         Chef chef=getChefByIdOrThrow(id);
         var saved = chefRepository.save(chef);
         return modelMapper.map(saved,ChefResponseDto.class);

     }
    @Override
    public List<ChefResponseDto> searchByName(String name) {

        var ContainsList =  chefRepository.findAll().stream().filter
                (chef ->  chef.getName().toLowerCase().contains(name.toLowerCase()));
        return ContainsList.map(chef -> modelMapper.map(chef, ChefResponseDto.class)).toList();
    }

    public List<ChefResponseDto> searchByResidence(String residenceName) {
        var ContainsList =  chefRepository.findAll().stream().filter
                (chef ->  chef.getResidence().toLowerCase().contains(residenceName.toLowerCase()));
        return ContainsList.map(chef -> modelMapper.map(chef, ChefResponseDto.class)).toList();

    }
//todo: continue search kitchen kind in controller,same with dishes create in service&controller.
    //todo: check both kitchen kind &Dishes searches

    public List<ChefResponseDto> searchKitchenKindName(String kitchenKindName) {

        var containsList = chefRepository.findAll().stream()
                .filter(chef -> chef.getKitchen_kinds().stream()
                        .map(Kitchen_Kind::getName)
                        .anyMatch(kindName -> kindName.toLowerCase().contains(kitchenKindName.toLowerCase())))
                .map(chef -> modelMapper.map(chef, ChefResponseDto.class))
                .toList();

        return containsList;
    }

    public List<ChefResponseDto> searchByDishName(String dishName) {

        var containsList = chefRepository.findAll().stream()
                .filter(chef -> chef.getAvailable_dishes().stream()
                        .map(Dish::getName)
                        .anyMatch(dishNameSearch -> dishNameSearch.toLowerCase().contains(dishName.toLowerCase())))
                .map(chef -> modelMapper.map(chef, ChefResponseDto.class))
                .toList();

        return containsList;
    }
    Chef getChefByIdOrThrow(long id) {
        return chefRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Chef", "id", id)
        );
    }

}
