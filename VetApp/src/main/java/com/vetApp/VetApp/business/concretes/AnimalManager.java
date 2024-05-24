package com.vetApp.VetApp.business.concretes;

import com.vetApp.VetApp.business.abstracts.AnimalService;
import com.vetApp.VetApp.core.exception.NotFoundException;
import com.vetApp.VetApp.core.utilities.Msg;
import com.vetApp.VetApp.dao.AnimalRepo;
import com.vetApp.VetApp.entities.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalManager implements AnimalService {
    private final AnimalRepo animalRepo;

    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(long id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Animal> findAll() {
        return this.animalRepo.findAll();
    }

    @Override
    public Animal update(Animal animal) {
        Optional<Animal> animalToUpdate = animalRepo.findById(animal.getId());
        if (animalToUpdate.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(long id) {
        Optional<Animal> animalToDelete = animalRepo.findById(id);

        if (animalToDelete.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }else {
            animalRepo.delete(animalToDelete.get());
            return true;
        }
    }

    @Override
    public List<Animal> getAnimalsByName(String name) {
        List<Animal> animalList = animalRepo.findByName(name);
        if (animalList.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }else {
            return animalRepo.findByName(name);
        }
    }
}
