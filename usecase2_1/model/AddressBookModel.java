package com.addressbook.app.model;

import java.util.Objects; 

public class AddressBookModel {
    private Long id; 
    private String name;
    private String phoneNumber;
    private String email;

    public AddressBookModel(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

 
    public AddressBookModel() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBookModel that = (AddressBookModel) o;
        
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(phoneNumber, that.phoneNumber) &&
               Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        // Hash code based on 'id' and other fields.
        return Objects.hash(id, name, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "AddressBookModel{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}