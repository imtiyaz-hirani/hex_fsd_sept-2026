package com.helpdesk.repository;

import com.helpdesk.mapper.TicketMapper;
import com.helpdesk.model.Customer;
import com.helpdesk.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TicketMapper ticketMapper;

    public CustomerRepository(JdbcTemplate jdbcTemplate, TicketMapper ticketMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.ticketMapper = ticketMapper;
    }

    public void insertCustomer(Customer customer) {
        String sql = "insert into customer values (?,?,?,?,?)";
        Object[] values = new Object[]{customer.getId(), customer.getName(), customer.getAge(), customer.getPlan().toString(), customer.getUser().getId()};
        jdbcTemplate.update(sql,values);
    }

    // delete customer by id
    public void deleteCustomer(Customer customer){
        String sql = "delete from customer where id=?";
        String sql1 = "delete from users where id=?";
        jdbcTemplate.update(sql, customer.getId());
        jdbcTemplate.update(sql1, customer.getUser().getId());

    }

    public List<Ticket> getAllTickets(String customerUsername) {
        String sql = """
                select t.id as ticket_id, t.subject, t.created_at, t.priority, t.status
                from customer c
                JOIN users u ON c.user_id = u.id
                JOIN tickets t ON t.customer_id = c.id
                where u.username = ?
                """;
        return jdbcTemplate.query(sql, ticketMapper, customerUsername);
    }
    // update customer details

}
