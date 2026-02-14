package com.alissar.cardealershipapp;
public class Customer {

    // Private variables matching database columns
    private int customerId;
    private String customerName;
    private String nationalId;
    private String phoneNumber;
    private String address;
    private String occupation;

    // Default constructor
    public Customer() {
    }

    // Constructor with all fields
    public Customer(int customerId, String customerName, String nationalId,
                    String phoneNumber, String address, String occupation) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.occupation = occupation;
    }

    // Constructor without ID (for new customers before DB insertion)
    public Customer(String customerName, String nationalId, String phoneNumber,
                    String address, String occupation) {
        this.customerName = customerName;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.occupation = occupation;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    // Optional: toString method for debugging
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
