package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.enums.Priority;
import com.springboot.helpdesk.enums.Status;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.model.*;
import com.springboot.helpdesk.repository.CustomerRepository;
import com.springboot.helpdesk.repository.ExecutiveRepository;
import com.springboot.helpdesk.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
 public class TicketServiceTest {
    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ExecutiveRepository executiveRepository;
    @Test
    public void getTicketsByCustomerIdTest(){
        TicketInfoDto dto1 = new TicketInfoDto(1L,"test1", Instant.now(), Priority.LOW, Status.OPEN,"test_name","executive_name","testmail@gmail.com");
        TicketInfoDto dto2 = new TicketInfoDto(2L,"test2", Instant.now(), Priority.HIGH, Status.OPEN,"test_name","executive_name","testmail@gmail.com");
        TicketInfoDto dto3 = new TicketInfoDto(3L,"test3", Instant.now(), Priority.HIGH, Status.OPEN,"test_name","executive_name","testmail@gmail.com");
        Customer customer = new Customer();
        customer.setId(1L);

        Customer customer2 = new Customer();
        customer.setId(2L);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer2));

        when(ticketRepository.getTicketByCustomerId(1L,PageRequest.of(0,5)))
                                        .thenReturn(List.of(dto1,dto2));
        when(ticketRepository.getTicketByCustomerId(2L,PageRequest.of(0,5)))
                .thenReturn(List.of(dto3));

        assertEquals(2,ticketService.getTicketsByCustomerId(1L,0,5).size());
        assertEquals(1,ticketService.getTicketsByCustomerId(2L,0,5).size());

        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).findById(2L);

        verify(ticketRepository, times(1))
                .getTicketByCustomerId(1L,PageRequest.of(0,5));
        verify(ticketRepository, times(1))
                .getTicketByCustomerId(2L,PageRequest.of(0,5));

        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(ticketRepository);
    }

    @Test
    public void getTicketsByCustomerIdTest_Invalid() {
     when(customerRepository.findById(5L)).thenThrow(ResourceNotFoundException.class);

    assertThrows(ResourceNotFoundException.class,
                ()-> ticketService.getTicketsByCustomerId(5L, 0, 5));

    verify(customerRepository, times(1)).findById(5L);

    verify(ticketRepository, times(0))
            .getTicketByCustomerId(1L, PageRequest.of(0, 5));

    verifyNoMoreInteractions(customerRepository);
    verifyNoMoreInteractions(ticketRepository);
}

    @Test
    public void saveTest(){
        /*
        * To call add method of service i need 2 things
        * 1. customerUsername
        * 2. ticketDto
        * this info comes from postman/react app via controller.
        * here , we need to define it ourself
        *
        * */
        // mock
        String customerUsername = "john";
        TicketDto ticketDto = new TicketDto("testSubject", "testIssue",Priority.HIGH);
        // Before making actual call, lets mock the repositories in that add method
        Customer customer = new Customer();
        when(customerRepository.getByUsername(customerUsername)).thenReturn(customer);

        // when this save method is call, don't go to DB and save, just assume it saved and give back any ticket class
         when(ticketRepository.save(any(Ticket.class))).thenReturn(any(Ticket.class));

        // actual call
        ticketService.add(customerUsername,ticketDto);

        // Tip: once u mock - it won't go to db but it still make a call.
        // verify if repo calls are happening only once
        verify(customerRepository,times(1)).getByUsername(customerUsername);
        verify(ticketRepository,times(1)).save(any(Ticket.class));
    }

    @Test
    public void assignExecutiveTest(){
        long executiveId = 1L;
        long ticketId = 1L;
        String managerUsername = "john";

        Executive executive = new Executive();
        executive.setId(1L);
        Manager manager = new Manager();
        User user = new User();
        user.setUsername("john");
        manager.setUser(user);
        executive.setManager(manager);

        Ticket ticket = new Ticket();
        ticket.setId(1L);

        when(executiveRepository.findById(executiveId)).thenReturn(Optional.of(executive));

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        when(ticketRepository.save(ticket)).thenReturn(any(Ticket.class));

        ticketService.assignExecutive(executiveId,ticketId,managerUsername);

        verify(executiveRepository, times(1)).findById(executiveId);
        verify(ticketRepository, times(1)).findById(ticketId);
        verify(ticketRepository, times(1)).save(ticket);

    }
}