package com.shop.product.dao;

import com.shop.product.model.Product;

import java.util.List;

/**
 * Data Access Object. 
 * 
 * @author SathishKumarS
 *
 */
public interface SportsShopDao {

    boolean addProduct(final Product product);

    boolean updateProductPrice(final Product product);

    boolean removeProduct(final Product product);

    List<Product> selectAllProducts();

    Product selectProductById(final Product product);
}
