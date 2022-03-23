package com.shop.product.service;

import com.shop.product.exceptions.InvalidProductException;
import com.shop.product.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Implements the services provided by the Shop application.
 * 
 * @author SathishKumarS
 * 
 */
public class ShopServiceImplementation implements ShopServices {

    private static ShopServices shopServices;
    private static final Map<String, List<Product>> SPORTS_KITS = new HashMap<String, List<Product>>();

    private ShopServiceImplementation() {

    }

    /**
     * Adds the product.
     * 
     * @param product
     * 
     */
    public void addProduct(final Product product) {
	    final List<Product> sportsProducts = new ArrayList<Product>();
		
	    if (!SPORTS_KITS.containsKey(product.getBrand())) {
       	    sportsProducts.add(product);
  	        SPORTS_KITS.put(product.getBrand(), sportsProducts);
	    } else {
	        SPORTS_KITS.get(product.getBrand()).add(product);
	    }
    }    

    /**
     * Selects the product.
     * 
     * @param product
     * @return Product object
     * 
     */
    public Product selectProduct(final Product product) {
		
	    if (SPORTS_KITS.containsKey(product.getBrand())) {

            for (final Product productDetails : SPORTS_KITS.get(product.getBrand())) {

                if (productDetails.getBrand().equals(product.getBrand()) && productDetails.getName().equals(product.getName())
			            && productDetails.getSize() == product.getSize()) {
		            return productDetails;
	            }
            }
	    } 
        throw new InvalidProductException("Product Not In Crew");
    }

    /**
     * Update the product price.
     * 
     * @param product
     * 
     */
    public void updateProductPrice(final Product product) {
        final Product productDetails = selectProduct(product);
		
        if (productDetails != null) {
	        productDetails.setPrice(product.getPrice());
        } else {
	        throw new InvalidProductException("Product Not In Crew");
        }
    }    

    /**
     * Remove the product.
     * 
     * @param product  
     *   
     */
    public void removeProduct(final Product product) {
	    final Product productDetails = selectProduct(product);
		
	    if (productDetails != null) {
	        SPORTS_KITS.get(product.getBrand()).remove(productDetails);
	    } else {
	        throw new InvalidProductException("Product Not In Crew");
	    }
    }    
	
    /**
     * Select all the products.
     *
     * @return List of products
     * 
     */
    public List<Product> selectAllProducts() {
        final List<Product> allProducts = new ArrayList<>();
		
        for (final List<Product> products : SPORTS_KITS.values()) {
	        allProducts.addAll(products);
	    }
	    return allProducts;
    }

    public static ShopServices getInstance() {

        if (shopServices == null) {
            shopServices = new ShopServiceImplementation();
        }
        return shopServices;
    }
}
