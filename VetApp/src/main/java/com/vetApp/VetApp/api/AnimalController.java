package com.vetApp.VetApp.api;

import com.vetApp.VetApp.business.abstracts.AnimalService;
import com.vetApp.VetApp.business.abstracts.CustomerService;
import com.vetApp.VetApp.core.config.modelMapper.ModelMapperService;
import com.vetApp.VetApp.core.result.Result;
import com.vetApp.VetApp.core.result.ResultData;
import com.vetApp.VetApp.core.utilities.ResultHelper;
import com.vetApp.VetApp.dto.request.animal.AnimalSaveRequest;
import com.vetApp.VetApp.dto.request.animal.AnimalUpdateRequest;
import com.vetApp.VetApp.dto.request.customer.CustomerSaveRequest;
import com.vetApp.VetApp.dto.request.customer.CustomerUpdateRequest;
import com.vetApp.VetApp.dto.response.AnimalResponse;
import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final ModelMapperService modelMapperService;
    private final AnimalService animalService;
    private final CustomerService customerService;


    //SAVE
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest){
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        Animal saveAnimal = new Animal(animalSaveRequest.getName(),animalSaveRequest.getSpecies(), animalSaveRequest.getBreed(), animalSaveRequest.getGender(),animalSaveRequest.getColour(),animalSaveRequest.getDateOfBirth(),this.customerService.get(animalSaveRequest.getCustomerId()));
                //this.modelMapperService.forRequest().map(animalSaveRequest, Animal.class);
        this.animalService.save(saveAnimal);
        return ResultHelper.success(this.modelMapperService.forResponse().map(saveAnimal, AnimalResponse.class));
    }
    //GET WITH ID
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id")long id){
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(animal, AnimalResponse.class));
    }
    //GET ALL
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> findAll(){
        return this.animalService.findAll();
    }

    //UPDATE
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest ){ // burda valid, animal anatasyonlarini uyandirip gecerli bir obje mi diye kontrol ediyor
        Animal animal = this.modelMapperService.forRequest().map(animalUpdateRequest, Animal.class);
        Customer customer = this.customerService.get(animalUpdateRequest.getCustomerId());
        animal.setCustomer(customer);
        this.animalService.update(animal);
        return ResultHelper.success(this.modelMapperService.forResponse().map(animal, AnimalResponse.class));
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id")long id){
        this.animalService.delete(id);
        return ResultHelper.ok();
    }
    //GET WITH NAME
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> getAnimalsByName(@PathVariable String name) {
        return this.animalService.getAnimalsByName(name);
    }
}
