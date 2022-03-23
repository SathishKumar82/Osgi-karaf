package com.shop.product.model;

import java.sql.Date;

/**
 * Store all the details of a product and it provides the single object.
 * 
 * @author SathishKumarS
 */
public class Product {
	
    private String name;
    private String brand;
    private double price;
    private char   size;
    private Date   manufactureDate;

    public Product() {
	    super();
    }

    public Product(final String name, final String brand, final double price, final char size, final Date manufactureDate) {
	    super();
	    this.name = name;
	    this.brand = brand;
	    this.price = price;
	    this.size = size;
	    this.manufactureDate = manufactureDate;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name.toUpperCase();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand.toUpperCase();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public char getSize() {
        return size;
    }

    public void setSize(final char size) {
        this.size = String.valueOf(size).toUpperCase().charAt(0);
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(final Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }
}
