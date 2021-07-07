package com.gmail.aba.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Gym {

    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;

    @OneToMany(mappedBy="gym")
    private Set<Client> clientSet = new HashSet<>();

    @OneToMany(mappedBy="gym")
    private Set<Employee> employees = new HashSet<>();


    public Gym() {
    }


    public Gym(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Set<Client> getClientSet() {
        return clientSet;
    }

    public void setClientSet(Set<Client> clientSet) {
        this.clientSet = clientSet;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


}
