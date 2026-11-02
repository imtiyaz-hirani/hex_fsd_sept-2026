package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.CustomerDto;
import com.springboot.helpdesk.dto.request.CustomerUpdateDto;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/api/customer/add")
    public void insertCustomer(@Valid @RequestBody CustomerDto customerDto){
         customerService.insertCustomer(customerDto);
    }

    @GetMapping("/api/customer/{id}") ///api/customer/1
    public Customer getById(@PathVariable long id){ //id=1
        return customerService.getById(id);
    }

    @GetMapping("/api/customer/all")
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @DeleteMapping("/api/customer/{id}")
    public void deleteById(@PathVariable long id){
        customerService.deleteById(id);
    }

    @PutMapping("/api/customer/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody CustomerUpdateDto dto){
        customerService.update(id, dto);
    }
    /*
      MultipartFile file =
      {name: '145432.jpg', lastModified: 1785732080891, lastModifiedDate: Mon Aug 03 2026 10:11:20 GMT+0530 (India Standard Time), webkitRelativePath: '', size: 555063, …}
    * */
    @PostMapping("/api/customer/upload")
    public void upload(Principal principal,
                            @RequestParam("file") MultipartFile file) throws IOException {
        String customerUsername = principal.getName();
        customerService.upload(customerUsername,file);
    }
    /*
    public CustomerDto getCustomerByUsername(Principal principal){
        //
    }

     */
}
