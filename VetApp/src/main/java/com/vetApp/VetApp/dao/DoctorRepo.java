package com.vetApp.VetApp.dao;

import com.vetApp.VetApp.entities.AvailableDate;
import com.vetApp.VetApp.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findByMail(String string);
}
