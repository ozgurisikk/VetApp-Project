package com.vetApp.VetApp.dao;

import com.vetApp.VetApp.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByName(String name);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate);
    Vaccine findByNameAndCode(String name, String code);



}
