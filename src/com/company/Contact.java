package com.company;

public class Contact {
    private String name;
    private String phone;

    public Contact(String csvString) {
        String[] element = csvString.split(";");
        this.name = element[0];
        this.phone = element[1];
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
