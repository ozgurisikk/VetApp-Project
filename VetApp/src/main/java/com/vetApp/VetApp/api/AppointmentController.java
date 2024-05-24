package com.vetApp.VetApp.api;

import com.vetApp.VetApp.business.abstracts.AnimalService;
import com.vetApp.VetApp.business.abstracts.AppointmentService;
import com.vetApp.VetApp.business.abstracts.DoctorService;
import com.vetApp.VetApp.core.config.modelMapper.ModelMapperService;
import com.vetApp.VetApp.core.result.Result;
import com.vetApp.VetApp.core.result.ResultData;
import com.vetApp.VetApp.core.utilities.ResultHelper;
import com.vetApp.VetApp.dto.request.appointment.AppointmentSaveRequest;
import com.vetApp.VetApp.dto.request.appointment.AppointmentUpdateRequest;
import com.vetApp.VetApp.dto.request.availableDate.AvailableDateUpdateRequest;
import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Appointment;
import com.vetApp.VetApp.entities.AvailableDate;
import com.vetApp.VetApp.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final ModelMapperService modelMapperService;
    private final DoctorService doctorService;
    private final AnimalService animalService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<Appointment> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest){
    Appointment appointmentSave = this.modelMapperService.forRequest().map(appointmentSaveRequest, Appointment.class);
        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());
        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());
        appointmentSave.setDoctor(doctor);
        appointmentSave.setAnimal(animal);
        this.appointmentService.save(appointmentSave);
        return ResultHelper.created(appointmentSave);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Appointment> get(@PathVariable("id")long id){
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(appointment);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findAll(){
        return this.appointmentService.findAll();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Appointment> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest ){ // burda valid, availableDate anatasyonlarini uyandirip gecerli bir obje mi diye kontrol ediyor
        Appointment appointment = this.modelMapperService.forRequest().map(appointmentUpdateRequest, Appointment.class);
        Doctor doctor = this.doctorService.get(appointmentUpdateRequest.getDoctorId());
        Animal animal = this.animalService.get(appointmentUpdateRequest.getAnimalId());
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);
        this.appointmentService.update(appointment);
        return ResultHelper.success(appointment);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id")long id){
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/search-by-animal-and-date")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findByAnimalAndDate(@RequestParam(value = "animal-id", required = false) Long animalId, @RequestParam LocalDate start, LocalDate finish){
        return this.appointmentService.findByDateAndAnimal(animalId, start, finish);
    }
    @GetMapping("/search-by-doctor-and-date")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findByDoctorAndDate(@RequestParam(value = "doctor-id", required = false) Long doctorId, @RequestParam LocalDate start, LocalDate finish){
        return this.appointmentService.findByDateAndDoctor(doctorId, start, finish);
    }

}
