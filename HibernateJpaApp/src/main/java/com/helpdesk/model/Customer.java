package com.helpdesk.model;

import jakarta.persistence.*;

@Entity // Creates a table
public class Customer { // c
    @Id // This makes id a Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This makes id auto_increment
    private int id;
    @Column(nullable = false) //This adds constraint NOT NULL
    private String name;
    private String city;

    @OneToOne
    private User user;

    public Customer() {
    }

    public Customer(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
