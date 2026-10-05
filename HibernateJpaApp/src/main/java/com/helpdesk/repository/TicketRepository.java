package com.helpdesk.repository;

import com.helpdesk.model.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Ticket ticket) {
        entityManager.persist(ticket);
    }
}
