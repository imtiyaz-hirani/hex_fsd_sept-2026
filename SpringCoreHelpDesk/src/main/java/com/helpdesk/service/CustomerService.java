package com.helpdesk.service;

import com.helpdesk.model.Customer;
import com.helpdesk.repository.CustomerRepository;
import com.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public void createCustomer(Customer customer) {
        // generate ids.
        int userId = (int)( Math.random()*100000);  //0.00 to 0.9999
        int customerId =  (int)( Math.random()*100000);

        // attaching to objects
        customer.getUser().setId(userId);
        customer.setId(customerId);

        // User object goes to DB
        userRepository.insertUser(customer.getUser());
        // Save Customer to DB
        customerRepository.insertCustomer(customer);
    }
}
