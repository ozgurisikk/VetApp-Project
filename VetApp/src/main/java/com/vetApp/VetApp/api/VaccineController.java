package com.vetApp.VetApp.api;

import com.vetApp.VetApp.business.abstracts.AnimalService;
import com.vetApp.VetApp.business.abstracts.VaccineService;
import com.vetApp.VetApp.core.config.modelMapper.ModelMapperService;
import com.vetApp.VetApp.core.result.Result;
import com.vetApp.VetApp.core.result.ResultData;
import com.vetApp.VetApp.core.utilities.ResultHelper;
import com.vetApp.VetApp.dto.request.vaccine.VaccineSaveRequest;
import com.vetApp.VetApp.dto.request.vaccine.VaccineUpdateRequest;
import com.vetApp.VetApp.dto.response.AnimalResponse;
import com.vetApp.VetApp.dto.response.VaccineResponseWithNoAnimal;
import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {
    private final ModelMapperService modelMapperService;
    private final VaccineService vaccineService;
    private final AnimalService animalService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<Vaccine> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest){
        Vaccine saveVaccine = new Vaccine(vaccineSaveRequest.getName(), vaccineSaveRequest.getCode(), vaccineSaveRequest.getProtectionStartDate(), vaccineSaveRequest.getProtectionFinishDate(), this.animalService.get(vaccineSaveRequest.getAnimalId()));

        this.vaccineService.save(saveVaccine);
        return ResultHelper.created(saveVaccine);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponseWithNoAnimal> get(@PathVariable("id")long id){
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(vaccine, VaccineResponseWithNoAnimal.class));
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findAll(){
        return this.vaccineService.findAll();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Vaccine> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest ){ // burda valid, customer anatasyonlarini uyandirip gecerli bir obje mi diye kontrol ediyor
        Vaccine vaccine = this.modelMapperService.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        Animal animal = this.animalService.get(vaccineUpdateRequest.getAnimalId());
        vaccine.setAnimal(animal);
        this.vaccineService.update(vaccine);
        return ResultHelper.success(vaccine);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id")long id){
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> getVaccinesByName(@PathVariable String name) {
        return vaccineService.getVaccinesByName(name);
    }
    @GetMapping("/animalid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> getVaccinesByAnimalId(@PathVariable("id") long id) {
        Animal animal = this.animalService.get(id);
        return animal.getVaccines();
    }

    @GetMapping("/near-vaccines")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine>  getVaccinesThatNear(
            @RequestParam(name = "startDate")LocalDate searchStartDate,
            @RequestParam(name = "finishDate")LocalDate searchFinishDate
            ){
        return this.vaccineService.getByProtectionFinishDateBetween(searchStartDate,searchFinishDate);
    }



}
