package com.helpdesk.service;

import com.helpdesk.dto.TicketRespDto;
import com.helpdesk.enums.Priority;
import com.helpdesk.enums.Status;
import com.helpdesk.exception.ResourceNotFoundException;
import com.helpdesk.mapper.TicketMapper;
import com.helpdesk.model.Customer;
import com.helpdesk.model.Ticket;
import com.helpdesk.repository.CustomerRepository;
import com.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;

    public TicketService(TicketRepository ticketRepository, CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void insertTicket(int customerId, String subject, String issue, Priority priority) {
        // Step 1: fetch Customer obj using given customerId
        Optional<Customer> optional  = customerRepository.getCustomerById(customerId);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("customer id invalid");

        Customer customer = optional.get();

        // Step 2: Prepare Ticket object
        Ticket ticket = new Ticket(subject,issue, priority, Status.OPEN);

        // Step 3: Attach customer obj to ticket obj
        ticket.setCustomer(customer);

        // Step 4: Add ticket object
        ticketRepository.insert(ticket);
    }

    public List<TicketRespDto> fetchTicketInfo() {
        // Step 1: Fetch all tickets from the repository : List<Ticket>
        List<Ticket> list = ticketRepository.fetchTicketInfoV1();
        // Step 2: convert the List<Ticket> to List<TicketRespDto> using Mapper.

        return list
                .stream()
                .map(TicketMapper ::convertEntityToDto)
                .toList();
    }
}
