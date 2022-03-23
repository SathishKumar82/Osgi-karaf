package com.shop.product.service;

import com.shop.product.dao.SportsShopDao;
import com.shop.product.dao.SportsShopDaoImpl;
import com.shop.product.exceptions.InvalidProductException;
import com.shop.product.model.Product;

import java.util.List;

/**
 * Implements ShopServiceDao.
 * 
 * @author SathishKumarS
 *
 */
public class ShopServiceImplementationV2 implements ShopServices {

    private static ShopServices shopServices;
    private static final SportsShopDao SPORTS_SHOP_DAO = SportsShopDaoImpl.getInstance();

    private ShopServiceImplementationV2() {

    }

    /**
     * Adds the product.
     * 
     * @param product
     * 
     */
    public void addProduct(final Product product) {
    	SPORTS_SHOP_DAO.addProduct(product);
    }

    /**
     * Retrieves all the products from database.
     * 
     * @return all products by list.
     * 
     */
    public List<Product> selectAllProducts() {
    	final List<Product> products = SPORTS_SHOP_DAO.selectAllProducts();
    	
        if (!products.isEmpty()) {
        	return products;
        }
        throw new InvalidProductException("No Such Products In Crew");
    }

    /**
     * Updates the product price.
     * 
     * @param product
     * 
     */
    public void updateProductPrice(final Product product) {
    	
    	if (!SPORTS_SHOP_DAO.updateProductPrice(product)) {
        	throw new InvalidProductException("Product Not In Crew");
    	}
    }

    /**
     * Removes the product.
     * 
     * @param product
     * 
     */
    public void removeProduct(final Product product) { 
    	
    	if (!SPORTS_SHOP_DAO.removeProduct(product)) {
            throw new InvalidProductException("Product Not In Crew");
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
		
	    for (final Product productDetails : SPORTS_SHOP_DAO.selectAllProducts()) {
	    	
	        if (productDetails.getBrand().equals(product.getBrand()) && productDetails.getName().equals(product.getName())
		            && String.valueOf(productDetails.getSize()).charAt(0) == product.getSize()) {
	            return productDetails;
	        } 
	    }
        throw new InvalidProductException("Product Not In Crew");
    }

    public static ShopServices getInstance() {

        if (shopServices == null) {
            shopServices = new ShopServiceImplementationV2();
        }
        return shopServices;
    }
}
