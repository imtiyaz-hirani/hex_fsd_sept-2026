package com.springboot.helpdesk.repository;

import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.dto.response.TicketInfoDtoV2;
import com.springboot.helpdesk.dto.response.TicketStatusStatDto;
import com.springboot.helpdesk.model.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("""
            select new com.springboot.helpdesk.dto.response.TicketInfoDto(t.id,t.subject,
            t.createdAt,t.priority,t.status,c.name,e.name,e.email)
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            where c.id = ?1
            """)
    List<TicketInfoDto> getTicketByCustomerId(Long customerId, Pageable pageable);
    @Query("""
            select new com.springboot.helpdesk.dto.response.TicketInfoDto(t.id,t.subject,
            t.createdAt,t.priority,t.status,c.name,e.name,e.email)
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            JOIN c.user u
            where u.username = ?1
            """)
    List<TicketInfoDto> getTicketByCustomerUsername(String customerUsername, Pageable pageable);

    @Query("""
            select new com.springboot.helpdesk.dto.response.TicketInfoDtoV2(
            t.id,
            e.id,
            e.name,
            t.subject,
            t.priority,
            t.status,
            t.createdAt,
            c.id,
            c.name
            )
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            JOIN e.user u
            where u.username=?1
            """)
    List<TicketInfoDtoV2> getTicketByExecutiveUsername(String executiveUsername, Pageable pageable);

    @Query("""
            select t.status as status,count(c.id) as numberOfCustomers
            from Ticket t JOIN t.customer c
            group by t.status
            """)
    List<TicketStatusStatDto> getTicketStatusStatWithNumCustomers();
}
