package com.vetApp.VetApp.business.abstracts;

import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {
    Animal save(Animal animal);
    Animal get(long id);
    List<Animal> findAll();
    Animal update(Animal animal);
    boolean delete(long id);
    List<Animal> getAnimalsByName(String name);

}
