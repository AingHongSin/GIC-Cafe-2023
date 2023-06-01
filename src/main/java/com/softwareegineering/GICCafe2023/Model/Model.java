package com.softwareegineering.GICCafe2023.Model;

public abstract class Model {
    private int id;

    // Constructor

    public Model() {
        // Default constructor
    }

    public Model(int id) {
        this.id = id;
    }

    // Getter and Setter for ID

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}