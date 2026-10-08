package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.Ticket;
import com.springboot.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/api/ticket/add/{customerId}")
    public void postTicket(@PathVariable Long customerId,
                           @Valid @RequestBody TicketDto ticketDto){
        ticketService.add(customerId, ticketDto);
    }
    // Done by Manager - activate the check after security config
    @PutMapping("/api/ticket/assign/{executiveId}/{ticketId}")
    public void assignExecutive(@PathVariable Long  executiveId ,
                                @PathVariable Long ticketId){
        ticketService.assignExecutive(executiveId, ticketId);
    }

    @GetMapping("/api/ticket/v1/{customerId}")
    public List<TicketInfoDto> getTicketsByCustomerId(@PathVariable Long customerId,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size){
        return ticketService.getTicketsByCustomerId(customerId,page,size);
    }

    @GetMapping("/api/ticket/v2")
    public List<TicketInfoDto> getTicketsByCustomerUsername(@RequestParam("username") String customerUsername,
                                             @RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        return ticketService.getTicketsByCustomerUsername(customerUsername,page,size);
    }
}
/*
    OPEN tickets
    ------------
    ticket 1 - id [Assign] -- select executive [executiveId] :- ticketId, executiveId
    ticket 2 - id [Assign]
 */
