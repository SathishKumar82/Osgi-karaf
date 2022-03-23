package com.shop.product.controller;

import com.shop.product.model.Product;

import java.util.List;
import java.util.Map;

/**
 * Shop controller interface.
 *
 * @author SathishKumarS
 */
public interface ShopController {

    Map addProduct(final List<Product> product);

    List selectProduct(final String brand, final String name, final char size);

    Map removeProduct(final List<Product> product);

    Map updateProduct(final List<Product> product);

    Map selectAllProduct(final int page, final int limit);
}
