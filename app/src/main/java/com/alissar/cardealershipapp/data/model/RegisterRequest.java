package com.alissar.cardealershipapp.data.model;


public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;

    public RegisterRequest(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    // Getters/Setters if needed by your JSON library
    public String getName(){return this.name;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getPhone(){return this.phone;}
}