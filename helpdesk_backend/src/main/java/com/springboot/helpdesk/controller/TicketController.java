package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
/*
    OPEN tickets
    ------------
    ticket 1 - id [Assign] -- select executive [executiveId] :- ticketId, executiveId
    ticket 2 - id [Assign]
 */
