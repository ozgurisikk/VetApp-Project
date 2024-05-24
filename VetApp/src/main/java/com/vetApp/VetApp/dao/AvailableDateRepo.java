package com.vetApp.VetApp.dao;

import com.vetApp.VetApp.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long id, LocalDate availableDate);
    @Query(nativeQuery = true, value = "SELECT ad1_0.id, ad1_0.available_date, ad1_0.doctor_id FROM available_date ad1_0 LEFT JOIN doctor d1_0 ON d1_0.id = ad1_0.doctor_id WHERE ad1_0.available_date = ?1 AND (ad1_0.doctor_id = ?2 OR d1_0.email =?3)")

    Optional<AvailableDate> findByAvailableDateAndDoctorIdOrDoctorMail(LocalDate availableDate, Long id, String email);
}
