package com.shop.product.view;

import com.shop.product.exceptions.UnableToAccessException;
import com.shop.product.model.Product;
import com.shop.product.attributes.ProductAttributes;
import com.shop.product.controller.ShopKeeper;
import com.shop.product.exceptions.InvalidProductException;

import static com.shop.product.view.SportsShop.LOGGER;
import static com.shop.product.view.SportsShop.SHOP_KEEPER;

/**
 * Customer class of the sports shop.
 * 
 * @author SathishKumarS
 * 
 */
public class Customer {

    /**
     * Selects the product.
     * 
     */
    void selectProduct() {
        final Product product = new Product();

        product.setBrand(ProductAttributes.getBrand());
        product.setName(ProductAttributes.getName());
        product.setSize(ProductAttributes.getSize());
        
        try {
            SportsShop.showProduct(SHOP_KEEPER.selectProduct(product));
        } catch (InvalidProductException exception) {
            LOGGER.warn(exception);
        } catch (UnableToAccessException exception) {
            LOGGER.error(exception);
        }
    }
}
