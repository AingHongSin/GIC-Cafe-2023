package com.softwareegineering.GICCafe2023.Model;

public class Size extends Model {
    private String sizeName;
    private double price;
	
	public Size() {
	}

	public Size(String sizeName, double price) {
		this.sizeName = sizeName;
		this.price = price;
	}

	public Size(int id, String sizeName, double price) {
		super(id);
		this.sizeName = sizeName;
		this.price = price;
	}
    

	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
