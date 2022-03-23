package com.shop.product.controller;

import com.shop.product.model.Product;
import com.shop.product.service.ShopServices;
import com.shop.product.service.ShopServiceImplementationV2;

import java.util.List;

/**
 * It controls and manage the requests and responses
 * It passes the requested data to implementation.
 * And also passes the response to view. 
 * 
 * @author SathishKumarS
 */
public class ShopKeeper {

    //private static final ShopServices SHOP_SERVICE_IMPLEMENTATION = ShopServiceImplementation.getInstance();
    private static final ShopServices SHOP_SERVICE_IMPLEMENTATION_V2 = ShopServiceImplementationV2.getInstance();
    private static ShopKeeper shopKeeper;

    public void addProduct(final Product product) {
    	//SHOP_SERVICE_IMPLEMENTATION.addProduct(product);
    	SHOP_SERVICE_IMPLEMENTATION_V2.addProduct(product);
    }

    public Product selectProduct(final Product product) {
        //SportsShop.showProduct(SHOP_SERVICE_IMPLEMENTATION.selectProduct(product));
        return SHOP_SERVICE_IMPLEMENTATION_V2.selectProduct(product);
    }

    public void updateProductPrice(final Product product) {
        //SHOP_SERVICE_IMPLEMENTATION.updateProductPrice(product));
        SHOP_SERVICE_IMPLEMENTATION_V2.updateProductPrice(product);
    }

    public void removeProduct(final Product product) {
        //SportsShop.showProduct(SHOP_SERVICE_IMPLEMENTATION.removeProduct(product));
        SHOP_SERVICE_IMPLEMENTATION_V2.removeProduct(product);
    }

    public List<Product> selectAllProducts() {
    	//SHOP_SERVICE_IMPLEMENTATION.selectAllProducts();
        return SHOP_SERVICE_IMPLEMENTATION_V2.selectAllProducts();
    }

    public static ShopKeeper getInstance() {
        if (shopKeeper == null) {
            shopKeeper = new ShopKeeper();
        }
        return shopKeeper;
    }
}
