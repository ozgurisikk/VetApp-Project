package com.vetApp.VetApp.dao;

import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AnimalRepo extends JpaRepository<Animal, Long> {

    List<Animal> findByName(String name);
}
