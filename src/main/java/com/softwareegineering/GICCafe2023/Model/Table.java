package com.softwareegineering.GICCafe2023.Model;

public class Table extends Model {
    private int tableNumber;
    private String tableType;
    private String tableStatus;
    
    public Table() {}

    public Table(int tableNumber, String tableType, String tableStatus) {
        this.tableNumber = tableNumber;
        this.tableType = tableType;
        this.tableStatus = tableStatus;
    }

    public Table(int tableId, int tableNumber, String tableType, String tableStatus) {
        super(tableId);
        this.tableNumber = tableNumber;
        this.tableType = tableType;
        this.tableStatus = tableStatus;
    }

    // Getters and setters

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
}
