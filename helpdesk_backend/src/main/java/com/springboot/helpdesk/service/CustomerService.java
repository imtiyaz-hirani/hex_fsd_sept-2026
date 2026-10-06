package com.springboot.helpdesk.service;

import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer insertCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("Invalid customer id");

        return optional.get();
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public void deleteById(long id) {
        // Check if id is valid
        getById(id);
        customerRepository.deleteById(id);
    }
}
