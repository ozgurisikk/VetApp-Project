package com.vetApp.VetApp.business.concretes;

import com.vetApp.VetApp.business.abstracts.DoctorService;
import com.vetApp.VetApp.core.exception.ConflictException;
import com.vetApp.VetApp.core.exception.NotFoundException;
import com.vetApp.VetApp.core.utilities.Msg;
import com.vetApp.VetApp.dao.DoctorRepo;
import com.vetApp.VetApp.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorManager implements DoctorService {
    private final DoctorRepo doctorRepo;
    @Override
    public Doctor save(Doctor doctor) {
        if (doctorRepo.findByMail(doctor.getMail()) != null){
            throw new ConflictException(Msg.ALREADY_SAVED_DOCTOR);
        }
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor get(long id) {
        return this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Doctor update(Doctor doctor) {
        Optional<Doctor> doctorToUpdate = doctorRepo.findById(doctor.getId());
        if (doctorToUpdate.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(long id) {
        Optional<Doctor> doctorToDelete = doctorRepo.findById(id);

        if (doctorToDelete.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }else {
            doctorRepo.delete(doctorToDelete.get());
            return true;
        }
    }
    public List<Doctor> findAll(){
        return this.doctorRepo.findAll();
    }
}
