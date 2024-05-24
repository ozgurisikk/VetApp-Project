package com.vetApp.VetApp.api;

import com.vetApp.VetApp.business.abstracts.AvailableDateService;
import com.vetApp.VetApp.business.abstracts.DoctorService;
import com.vetApp.VetApp.core.config.modelMapper.ModelMapperService;
import com.vetApp.VetApp.core.result.Result;
import com.vetApp.VetApp.core.result.ResultData;
import com.vetApp.VetApp.core.utilities.ResultHelper;
import com.vetApp.VetApp.dto.request.availableDate.AvailableDateSaveRequest;
import com.vetApp.VetApp.dto.request.availableDate.AvailableDateUpdateRequest;
import com.vetApp.VetApp.entities.AvailableDate;
import com.vetApp.VetApp.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/availabledates")
@RequiredArgsConstructor
public class AvailableDateController {

    private final ModelMapperService modelMapperService;
    private final AvailableDateService availableDateService;
    private final DoctorService doctorService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDate> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        Doctor doctor = this.doctorService.get(availableDateSaveRequest.getDoctorId());
        AvailableDate saveAvailableDate = new AvailableDate(availableDateSaveRequest.getAvailableDate(), this.doctorService.get(availableDateSaveRequest.getDoctorId()));

        this.availableDateService.save(saveAvailableDate);
        return ResultHelper.created(saveAvailableDate);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDate> get(@PathVariable("id")long id){
        AvailableDate availableDate = this.availableDateService.get(id);
        return ResultHelper.success(availableDate);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> findAll(){
        return this.availableDateService.findAll();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDate> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest ){ // burda valid, availableDate anatasyonlarini uyandirip gecerli bir obje mi diye kontrol ediyor
        AvailableDate availableDate = this.modelMapperService.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        Doctor doctor = this.doctorService.get(availableDateUpdateRequest.getDoctorId());
        availableDate.setDoctor(doctor);
        this.availableDateService.update(availableDate);
        return ResultHelper.success(availableDate);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id")long id){
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }
}
