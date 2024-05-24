package com.vetApp.VetApp.business.abstracts;

import com.vetApp.VetApp.entities.Appointment;
import com.vetApp.VetApp.entities.AvailableDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AppointmentService {
    Appointment save(Appointment appointment);
    Appointment get(long id);
    List<Appointment> findAll();
    Appointment update(Appointment appointment);
    boolean delete(long id);
    List<Appointment> findByDateAndAnimal (Long animalId, LocalDate start, LocalDate finish);
    List<Appointment> findByDateAndDoctor (Long doctorId, LocalDate start, LocalDate finish);

}
