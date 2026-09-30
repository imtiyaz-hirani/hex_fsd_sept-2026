package com.helpdesk.model;

import com.helpdesk.enums.Priority;
import com.helpdesk.enums.Status;

import java.time.LocalDate;

public class Ticket {
    private int id;
    private String subject;
    private String issue;
    private LocalDate createdAt;
    private Priority priority;
    private Status status;

    private Customer customer;

    //constructor , getter, setter, toString
}
