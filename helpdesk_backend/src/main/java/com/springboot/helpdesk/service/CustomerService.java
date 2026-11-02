package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.CustomerDto;
import com.springboot.helpdesk.dto.request.CustomerUpdateDto;
import com.springboot.helpdesk.enums.Role;
import com.springboot.helpdesk.exception.InvalidCallException;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.CustomerMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.User;
import com.springboot.helpdesk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final static String uploadDir = "D:/hex_fsd_sept_2026/helpdesk-ui/public/uploads/";

    public void insertCustomer(CustomerDto dto) {
        // Step 1: Prepare user obj and save it in DB
        User user = userService.getUserObj(
                dto.username(),
                dto.password(),
                Role.CUSTOMER
        );

        // Step 1.5 : Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public void update(Long id, CustomerUpdateDto dto) {
        Customer customer = getById(id);
        boolean flag = false;

        if(!(dto.name()  == null) && !(dto.name().trim().isEmpty())) {    // If dto.name() is NOT(!) empty
            customer.setName(dto.name());
            flag = true;
        }

        if(!(dto.city() == null) && !(dto.city().trim().isEmpty())) {
            customer.setCity(dto.city());
            flag = true;
        }

        if(!flag)
            throw new InvalidCallException("Update op terminated");

        customerRepository.save(customer);

    }

    public void upload(String customerUsername, MultipartFile file) throws IOException {
        // fetch customer from username
        Customer customer  = customerRepository.getByUsername(customerUsername);
        if(file == null || file.isEmpty())
            throw new NullPointerException("Please upload valid file");

        logger.info("uploaded file name {}", file.getOriginalFilename());
        logger.info("File size {}",file.getSize());
        String ext = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        logger.info("File extension {}",  ext);

        // Original file name
        String originalFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename() ;

        Files.copy(file.getInputStream() , Paths.get(uploadDir + originalFileName), StandardCopyOption.REPLACE_EXISTING);

        // Save filename in the DB
        customer.setIdUrl(originalFileName);

        customerRepository.save(customer);
    }
}
