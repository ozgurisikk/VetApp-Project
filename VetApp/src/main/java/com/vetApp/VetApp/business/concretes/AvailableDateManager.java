package com.vetApp.VetApp.business.concretes;

import com.vetApp.VetApp.business.abstracts.AvailableDateService;
import com.vetApp.VetApp.core.exception.ConflictException;
import com.vetApp.VetApp.core.exception.NotFoundException;
import com.vetApp.VetApp.core.utilities.Msg;
import com.vetApp.VetApp.dao.AvailableDateRepo;
import com.vetApp.VetApp.dao.DoctorRepo;
import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.AvailableDate;
import com.vetApp.VetApp.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements AvailableDateService {
    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(long id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepo.findAll();
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        Optional<AvailableDate> updateAvailableDate = availableDateRepo.findById(availableDate.getId());
        if (updateAvailableDate.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public boolean delete(long id) {
        Optional<AvailableDate> availableDateToDelete = availableDateRepo.findById(id);

        if (availableDateToDelete.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }else {
            availableDateRepo.delete(availableDateToDelete.get());
            return true;
        }
    }
}
