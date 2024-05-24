package com.vetApp.VetApp.api;

import com.vetApp.VetApp.business.abstracts.CustomerService;
import com.vetApp.VetApp.core.config.modelMapper.ModelMapperService;
import com.vetApp.VetApp.core.exception.NotFoundException;
import com.vetApp.VetApp.core.result.Result;
import com.vetApp.VetApp.core.result.ResultData;
import com.vetApp.VetApp.core.utilities.Msg;
import com.vetApp.VetApp.core.utilities.ResultHelper;
import com.vetApp.VetApp.dto.request.customer.CustomerSaveRequest;
import com.vetApp.VetApp.dto.request.customer.CustomerUpdateRequest;
import com.vetApp.VetApp.entities.Customer;
import com.vetApp.VetApp.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapperService modelMapperService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<Customer> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest){
        Customer saveCustomer = this.modelMapperService.forRequest().map(customerSaveRequest, Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(saveCustomer);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Customer> get(@PathVariable("id")long id){
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(customer);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll(){
        return this.customerService.findAll();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Customer> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest ){ // burda valid, customer anatasyonlarini uyandirip gecerli bir obje mi diye kontrol ediyor
        Customer customerUpdate = this.modelMapperService.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(customerUpdate);
        return ResultHelper.success(customerUpdate);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id")long id){
        this.customerService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomersByName(@PathVariable String name) {
        return this.customerService.getCustomersByName(name);
    }


}
