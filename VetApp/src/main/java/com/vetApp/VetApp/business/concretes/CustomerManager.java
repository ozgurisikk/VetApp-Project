package com.vetApp.VetApp.business.concretes;

import com.vetApp.VetApp.business.abstracts.CustomerService;
import com.vetApp.VetApp.core.exception.ConflictException;
import com.vetApp.VetApp.core.exception.NotFoundException;
import com.vetApp.VetApp.core.utilities.Msg;
import com.vetApp.VetApp.dao.CustomerRepo;
import com.vetApp.VetApp.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepo customerRepo;
    @Override
    public Customer save(Customer customer) {
        if (customerRepo.findByMail(customer.getMail()) != null){
            throw new ConflictException(Msg.ALREADY_SAVED_CUSTOMER);
        }
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(long id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Customer update(Customer customer) {
        Optional<Customer> animalToUpdate = customerRepo.findById(customer.getId());
        if (animalToUpdate.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.customerRepo.save(customer);
    }

    @Override
    public boolean delete(long id) {
        Optional<Customer> customerToDelete = customerRepo.findById(id);

        if (customerToDelete.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }else {
            customerRepo.delete(customerToDelete.get());
            return true;
        }
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        List<Customer> customerList = customerRepo.findByName(name);
        if (customerList.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }else {
            return customerRepo.findByName(name);
        }
    }

    public List<Customer> findAll(){
        return this.customerRepo.findAll();
    }
}
