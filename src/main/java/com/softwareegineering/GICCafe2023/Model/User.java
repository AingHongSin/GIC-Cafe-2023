package com.softwareegineering.GICCafe2023.Model;

import java.util.Date;
import java.util.Map;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String sex;
    private Date dob;
    private int age;
    private String username;
    private String password;
    private Map<String, Object> attributes;

    public User() {
        
    }

    public User(int id, String firstName, String lastName, String sex, Date dob, int age, String username,
            String password, Map<String, Object> attributes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dob = dob;
        this.age = age;
        this.username = username;
        this.password = password;
        this.attributes = attributes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // Method to add attributes
    public void addAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    // Method to get attributes
    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }
}

