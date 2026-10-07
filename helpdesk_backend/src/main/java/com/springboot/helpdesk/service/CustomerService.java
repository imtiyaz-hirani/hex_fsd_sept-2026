package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.CustomerDto;
import com.springboot.helpdesk.enums.Role;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.CustomerMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.User;
import com.springboot.helpdesk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;

    public void insertCustomer(CustomerDto dto) {
        // Step 1: Prepare user obj and save it in DB
        User user = userService.getUserObj(
                dto.username(),
                dto.password(),
                Role.CUSTOMER
        );

        // Map CustomerDto to Customer entity
        Customer customer = CustomerMapper.convertDtoToEntity(dto);
        // Attach user to Customer
        customer.setUser(user);

        // Save Customer entity
         customerRepository.save(customer);
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
