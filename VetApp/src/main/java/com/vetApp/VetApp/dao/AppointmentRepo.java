package com.vetApp.VetApp.dao;

import com.vetApp.VetApp.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAvailableDateAndDoctorId(LocalDateTime date, Long id);
    Optional<Appointment> findByAvailableDateAndDoctorIdAndAnimalId(LocalDateTime date, Long doctorId, Long animalId);
    List<Appointment> findByAvailableDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findByDoctorIdAndAvailableDateBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findByAnimalIdAndAvailableDateBetween(Long animalId, LocalDateTime startDate, LocalDateTime endDate);
}
