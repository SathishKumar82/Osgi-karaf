package com.shop.product.service;

import com.shop.product.model.Product;

import java.util.List;
import java.util.Map;

/**
 * Rest service.
 *
 * @author SathishKumarS
 */
public interface RestService {

    Map addProduct(final List<Product> product);

    Map selectAllProducts(final int page, int noOfEntities);

    List selectProduct(final Product product);

    Map updateProductPrice(final List<Product> products);

    Map removeProduct(final List<Product> products);
}
