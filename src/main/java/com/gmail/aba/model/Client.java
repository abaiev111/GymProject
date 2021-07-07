package com.gmail.aba.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private Integer age;
    private String dayOfBirth;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;


    public Client() {
    }

    public Client(String firstName, String lastName, Integer age, String dayOfBirth, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dayOfBirth = dayOfBirth;
        this.phone = phone;
        this.email = email;
    }

    public Client(String firstName, String lastName, Integer age, String dayOfBirth, String phone, String email, Gym gym) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dayOfBirth = dayOfBirth;
        this.phone = phone;
        this.email = email;
        this.gym = gym;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }


}
