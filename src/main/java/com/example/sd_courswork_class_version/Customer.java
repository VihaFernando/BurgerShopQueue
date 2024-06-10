package com.example.sd_courswork_class_version;

public class Customer {
    private String firstName;
    private String lastName;
    private int burgersRequired;

    public Customer(String firstName, String lastName, int burgersRequired) {    // function for take customer first name last name and required burgers
        this.firstName = firstName;
        this.lastName = lastName;
        this.burgersRequired = burgersRequired;
    }

    public String getFirstName() {
        return this.firstName;
    }   // for return first name

    public String getLastName() {
        return this.lastName;
    }     // for return last name

    public int getBurgersRequired() {
        return this.burgersRequired;
    }     // for return no.of burgers

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }         // for return full name
}

