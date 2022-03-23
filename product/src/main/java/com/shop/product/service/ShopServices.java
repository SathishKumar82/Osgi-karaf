package com.shop.product.service;

import com.shop.product.model.Product;

import java.util.List;

/**
 * These Services are provided by sports shop application.
 * 
 * @author SathishKumarS
 * 
 */
public interface ShopServices {
	
    void addProduct(final Product product);
    
    List selectAllProducts();
		
    Product selectProduct(final Product product);
		
    void updateProductPrice(final Product product);
		
    void removeProduct(final Product product);
}

