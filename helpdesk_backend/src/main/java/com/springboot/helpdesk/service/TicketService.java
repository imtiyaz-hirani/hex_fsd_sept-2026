package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.enums.Status;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.TicketMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.Executive;
import com.springboot.helpdesk.model.Ticket;
import com.springboot.helpdesk.repository.CustomerRepository;
import com.springboot.helpdesk.repository.ExecutiveRepository;
import com.springboot.helpdesk.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;

    public void add(Long customerID, TicketDto ticketDto) {
        // Step 1: Fetch Customer Object from DB using given customerId
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(()-> new ResourceNotFoundException("Customer id Invalid"));

        // Step 2: Map TicketDto to Ticket entity
        Ticket ticket = TicketMapper.convertDtoToEntity(ticketDto);

        // Step 3: Attach customer to Ticket
        ticket.setCustomer(customer);

        // Step 3.5: Assign Status as Open to Ticket
        ticket.setStatus(Status.OPEN);

        // Step 4: Insert Ticket in DB
        ticketRepository.save(ticket);
    }

    public void assignExecutive(Long executiveId, Long ticketId) {
        // Step 1: Fetch executive from DB using executiveId
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(()-> new ResourceNotFoundException("executive not found"));

        // Step 2: Fetch ticket from DB using ticketId
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket not found"));

        // Step 3: Attach executive to ticket
        ticket.setExecutive(executive);

        // Step 4: Save ticket
        ticketRepository.save(ticket);
    }

    public List<TicketInfoDto> getTicketsByCustomerId(Long customerId, int page, int size) {
        // Create an Instance of Pageable interface using page and size values thru PageRequest
        Pageable pageable =  PageRequest.of(page,size);
        return ticketRepository.getTicketByCustomerId(customerId,pageable);
    }
}
/*
void m1(){
    if(this == true)
        throw new ResourceNotFoundException("Customer id Invalid");
}

()->new ResourceNotFoundException("Customer id Invalid")
()->{
    if(this == true)
        throw new ResourceNotFoundException("Customer id Invalid");
}
* */