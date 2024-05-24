package com.vetApp.VetApp.business.abstracts;

import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    Doctor save(Doctor doctor);
    Doctor get(long id);
    List<Doctor> findAll();
    Doctor update(Doctor doctor);
    boolean delete(long id);
}
