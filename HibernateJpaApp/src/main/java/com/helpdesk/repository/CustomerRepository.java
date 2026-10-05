package com.helpdesk.repository;

import com.helpdesk.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insert(Customer customer) {
        entityManager.persist(customer);
    }

    public List<Customer> getAllCustomers() {
        String jpql="select c from Customer c"; // you query the class Customer
        String hql = "from Customer c";  // no select statement
        String nativeSql = "select * from customer"; // u query the table

        return entityManager
                .createQuery(jpql, Customer.class)
                .getResultList();
        /*
        return entityManager
                .createQuery(hql, Customer.class)
                .getResultList();


        return entityManager
                .createNativeQuery("select * from customer", Customer.class)
                .getResultList(); // List<Object> -- ClassCastException

        */
    }

    public Optional<Customer> getCustomerById(int customerId) {
        return Optional
                .ofNullable(entityManager
                                .find(Customer.class, customerId));
    }
}
/*
Native SQL: select * from customer c
* JPQL: select c from Customer c
* HQL: from Customer c
* */