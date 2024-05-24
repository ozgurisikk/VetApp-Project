package com.vetApp.VetApp.business.concretes;

import com.vetApp.VetApp.business.abstracts.VaccineService;
import com.vetApp.VetApp.core.exception.ConflictException;
import com.vetApp.VetApp.core.exception.NoVaccineException;
import com.vetApp.VetApp.core.exception.NotFoundException;
import com.vetApp.VetApp.core.utilities.Msg;
import com.vetApp.VetApp.dao.AnimalRepo;
import com.vetApp.VetApp.dao.VaccineRepo;
import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineManager implements VaccineService {
    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;

    @Override
    public Vaccine save(Vaccine vaccine) {

        List<Vaccine> vaccineListOfAnimal = vaccine.getAnimal().getVaccines();
        for (Vaccine vaccine1 : vaccineListOfAnimal){
            if (vaccine1.getCode().equals(vaccine.getCode()) && vaccine1.getProtectionFinishDate().isAfter(LocalDate.now())){
                throw new ConflictException(Msg.CONFLICT);
            }
        }
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine get(long id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Vaccine> findAll() {
        return this.vaccineRepo.findAll();
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        Optional<Vaccine> vaccineToUpdate = vaccineRepo.findById(vaccine.getId());
        if (vaccineToUpdate.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public boolean delete(long id) {
        Optional<Vaccine> vaccineToDelete = vaccineRepo.findById(id);

        if (vaccineToDelete.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }else {
            vaccineRepo.delete(vaccineToDelete.get());
            return true;
        }
    }
    @Override
    public List<Vaccine> getVaccinesByName(String name) {
        return vaccineRepo.findByName(name);
    }

    @Override
    public List<Vaccine> getByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByProtectionFinishDateBetween(startDate,finishDate);
        if (vaccineList.isEmpty()){
            throw new NoVaccineException(Msg.NONE_NEAR_VACCINE);

        }
        return vaccineList;
    }


}

