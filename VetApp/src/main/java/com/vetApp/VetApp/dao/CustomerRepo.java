package com.vetApp.VetApp.dao;

import com.vetApp.VetApp.entities.Customer;
import com.vetApp.VetApp.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);
    Customer findByMail(String mail);
}
