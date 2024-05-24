package com.vetApp.VetApp.business.abstracts;

import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Vaccine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface VaccineService {
    Vaccine save(Vaccine vaccine);
    Vaccine get(long id);
    List<Vaccine> findAll();
    Vaccine update(Vaccine vaccine);
    boolean delete(long id);
    List<Vaccine> getVaccinesByName(String name);
    List<Vaccine> getByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate);

}
