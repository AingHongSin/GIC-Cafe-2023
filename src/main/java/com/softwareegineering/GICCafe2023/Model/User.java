package com.softwareegineering.GICCafe2023.Model;

import java.sql.Date;

public class User extends Model {
    private String firstName;
    private String lastName;
    private String sex;
    private String role;
    private Date dob;
    private Date hireDate;
    private int age;
    private String username;
    private String password;
    private String image_url;
    private int served;
    private Date lastLogin;



    public User(int id, String firstName, String lastName, String sex, String role, Date dob, int age, String username,
            String password, String image_url) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.role = role;
        this.dob = dob;
        this.age = age;
        this.username = username;
        this.password = password;
        this.image_url = image_url;
    }



    public User(int id, String firstName, String lastName, String sex, String role, Date dob, Date hireDate, int age,
            String username, String password, String image_url) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.role = role;
        this.dob = dob;
        this.hireDate = hireDate;
        this.age = age;
        this.username = username;
        this.password = password;
        this.image_url = image_url;
    }



    public User(int id, String firstName, String lastName, String sex, String role, Date dob, Date hireDate, int age,
            String username, String password, String image_url, int served, Date lastLogin) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.role = role;
        this.dob = dob;
        this.hireDate = hireDate;
        this.age = age;
        this.username = username;
        this.password = password;
        this.image_url = image_url;
        this.served = served;
        this.lastLogin = lastLogin;
    }



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }



    public User() {
        // Default constructor
    }

    // Getters and Setters

    public String getFirstName() {  return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public Date getDob() { return dob; }

    public void setDob(Date dob) { this.dob = dob; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getUsername() {  return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password; }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public int getServed() {
        return served;
    }

    public void setServed(int served) {
        this.served = served;
    }
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

}
