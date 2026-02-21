package com.alissar.cardealershipapp.data.model;


public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String nationalId;
    private String address;
    private String occupation;

    public RegisterRequest(String firstName,String lastName, String email, String password, String phone, String nationalId, String address, String occupation) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.nationalId = nationalId;
        this.address = address;
        this.occupation = occupation;
    }

    // Getters/Setters if needed by your JSON library
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public String getAddress() {
        return this.address;
    }

    public String getOccupation() {
        return this.occupation;
    }

}