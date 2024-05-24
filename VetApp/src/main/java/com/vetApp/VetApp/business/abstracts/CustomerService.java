package com.vetApp.VetApp.business.abstracts;

import com.vetApp.VetApp.entities.Animal;
import com.vetApp.VetApp.entities.Customer;
import com.vetApp.VetApp.entities.Vaccine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    Customer save(Customer customer);
    Customer get(long id);
    List<Customer> findAll();

    Customer update(Customer customer);
    boolean delete(long id);
    List<Customer> getCustomersByName(String name);

}
