package com.vetApp.VetApp.api;

import com.vetApp.VetApp.business.abstracts.DoctorService;
import com.vetApp.VetApp.core.config.modelMapper.ModelMapperService;
import com.vetApp.VetApp.core.result.Result;
import com.vetApp.VetApp.core.result.ResultData;
import com.vetApp.VetApp.core.utilities.ResultHelper;
import com.vetApp.VetApp.dto.request.customer.CustomerSaveRequest;
import com.vetApp.VetApp.dto.request.customer.CustomerUpdateRequest;
import com.vetApp.VetApp.dto.request.doctor.DoctorSaveRequest;
import com.vetApp.VetApp.entities.Customer;
import com.vetApp.VetApp.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final ModelMapperService modelMapperService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<Doctor> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest){
        Doctor saveDoctor = this.modelMapperService.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorService.save(saveDoctor);
        return ResultHelper.created(saveDoctor);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Doctor> get(@PathVariable("id")long id){
        Doctor doctor = this.doctorService.get(id);
        return ResultHelper.success(doctor);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> findAll(){
        return this.doctorService.findAll();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Doctor> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest ){ // burda valid, customer anatasyonlarini uyandirip gecerli bir obje mi diye kontrol ediyor
        Doctor doctorUpdate = this.modelMapperService.forRequest().map(customerUpdateRequest, Doctor.class);
        this.doctorService.update(doctorUpdate);
        return ResultHelper.success(doctorUpdate);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id")long id){
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }
}
