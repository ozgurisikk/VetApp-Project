package com.vetApp.VetApp.business.abstracts;

import com.vetApp.VetApp.entities.AvailableDate;
import com.vetApp.VetApp.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AvailableDateService {
    AvailableDate save(AvailableDate availableDate);
    AvailableDate get(long id);
    List<AvailableDate> findAll();
    AvailableDate update(AvailableDate availableDate);
    boolean delete(long id);
}
