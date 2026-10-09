package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.dto.response.TicketInfoDtoV2;
import com.springboot.helpdesk.dto.response.TicketStatusStatDto;
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


    /*
    Get tickets by executive username and display following info:
    ticketId
    executiveId
    executiveName
    ticketSubject
    ticketPriority
    ticketStatus
    ticketCreatedAt
    customerId
    customerName

    Make pagination optional
    * */

    @GetMapping("/api/ticket/executive")
    public List<TicketInfoDtoV2> getTicketByExecutiveUsername(
            @RequestParam("username") String executiveUsername,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size)
    {
        return ticketService.getTicketByExecutiveUsername(executiveUsername, page, size);
    }

    /*
     Ticket Status  |  Num. of Customers
     OPEN               34
     IN_PROCESS         67
     CLOSED             123
    * */
    @GetMapping("/api/ticket/status/customers/stat")
    public List<TicketStatusStatDto> getTicketStatusStatWithNumCustomers(){
        return ticketService.getTicketStatusStatWithNumCustomers();
    }

}
/*
    OPEN tickets
    ------------
    ticket 1 - id [Assign] -- select executive [executiveId] :- ticketId, executiveId
    ticket 2 - id [Assign]
 */
