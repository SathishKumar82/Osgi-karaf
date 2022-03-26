package com.shop.product.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;

/**
 * Store all the details of a product and it provides the single object.
 * 
 * @author SathishKumarS
 */
public class Product {

    @NotEmpty(message = "Name Is Mandatory", groups = {AddCheck.class, SelectCheck.class, UpdateCheck.class, DeleteCheck.class})
    @Pattern(regexp = "(?i)bat|ball|stump|gloves|helmet", groups = {AddCheck.class, SelectCheck.class, UpdateCheck.class, DeleteCheck.class}, message = "Product Is Not Available...  Select Anyone Of Them (Bat, Ball, Stump, Gloves, Helmet)")
    private String name;

    @NotEmpty(message = "Brand Is Mandatory", groups = {AddCheck.class, SelectCheck.class, UpdateCheck.class, DeleteCheck.class})
    @Pattern(regexp = "(?i)ss|sg|mrf|nike", groups = {AddCheck.class, SelectCheck.class, UpdateCheck.class, DeleteCheck.class}, message = "Brand Is Not Available...  Select Anyone Of Them (SS, SG, MRF, RBK, NIKE)")
    private String brand;

    @NotNull(message = "Price Is Mandatory", groups = {AddCheck.class, UpdateCheck.class})
    private Double price;

    @NotEmpty(message = "Size Is Mandatory", groups = {AddCheck.class, SelectCheck.class, UpdateCheck.class, DeleteCheck.class})
    @Pattern(regexp = "(?i)s|m|l", groups = {AddCheck.class, SelectCheck.class, UpdateCheck.class, DeleteCheck.class}, message = "Size Is Invalid...  Re-Enter Valid Size(Use Only S, M, L)")
    private String size;

    @NotNull(message = "Manufactured Date Is Mandatory", groups = {AddCheck.class})
    @Past(message = "Please Enter Valid Date", groups = {AddCheck.class})
    private Date manufactureDate;

    public Product() {
	    super();
    }

    public Product(final String name, final String brand, final double price, final char size, final Date manufactureDate) {
	    super();
	    this.name = name;
	    this.brand = brand;
	    this.price = price;
	    this.size = String.valueOf(size);
	    this.manufactureDate = manufactureDate;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand.toUpperCase();
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public char getSize() {
        return  size.toUpperCase().charAt(0);
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(final Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }
}