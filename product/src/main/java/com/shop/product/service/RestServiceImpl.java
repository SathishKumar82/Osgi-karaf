package com.shop.product.service;

import com.shop.product.dao.SportsShopDao;
import com.shop.product.dao.SportsShopDaoImpl;
import com.shop.product.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest service implementation.
 *
 * @author SathishKumarS
 */
public class RestServiceImpl implements RestService {

    private static RestService restService;
    private static final SportsShopDao SPORTS_SHOP_DAO = SportsShopDaoImpl.getInstance();

    private RestServiceImpl() {

    }

    /**
     * Adds the product.
     *
     * @param products
     *
     * @return Boolean.
     */
    @Override
    public Map addProduct(final List<Product> products) {
        final Map map = new HashMap<>();

        for (int index = 0; index < products.size(); index++) {
            boolean isAdded = SPORTS_SHOP_DAO.addProduct(products.get(index));

            map.put(String.format("%s %o","Successfully added product ", index + 1), isAdded);
        }
        return map;
    }

    /**
     * Selects all the products.
     *
     * @return List of products.
     */
    @Override
    public Map selectAllProducts(final int page, final int noOfEntities) {
        final List products = SPORTS_SHOP_DAO.selectAllProducts();
        final Map response = new HashMap<>();
        int starting = 0, ending = 0;

        if (page > 0 && noOfEntities > 0) {
            starting = (page - 1) * noOfEntities;
            ending = starting + noOfEntities;
        }

        if (starting < products.size() && ending < products.size()) {
            response.put("total", products.size());
            response.put("count", noOfEntities);
            response.put("data", products.subList(starting, ending));

            return response;
        } else if (starting < products.size()) {
            response.put("total", products.size());
            response.put("count", noOfEntities);
            response.put("data", products.subList(starting, products.size()));

            return response;
        } else {
            response.put("", "Page Not Found");
            return response;
        }
    }

    /**
     * Selects the product.
     *
     * @param product
     *
     * @return Product
     */
    @Override
    public List selectProduct(final Product product) {
        final List products= new ArrayList<>();
        final Product selectedProduct = SPORTS_SHOP_DAO.selectProductById(product);

        if (selectedProduct == null) {
            products.add("Product Not Available");
            return products;
        } else {
            products.add(selectedProduct);
            return products;
        }
    }

    /**
     * Updates the product price.
     *
     * @param products
     *
     * @return Boolean.
     */
    @Override
    public Map updateProductPrice(final List<Product> products) {
        final Map map = new HashMap<>();

        for (int index = 0; index < products.size(); index++) {
            boolean isAdded = SPORTS_SHOP_DAO.updateProductPrice(products.get(index));

            map.put(String.format("%s %o","Successfully updated product ", index + 1), isAdded);
        }
        return map;
    }

    /**
     * Removes the product.
     *
     * @param products
     *
     * @return Boolean
     */
    @Override
    public Map removeProduct(final List<Product> products) {
        final Map map = new HashMap<>();

        for (int index = 0; index < products.size(); index++) {
            final Product product = SPORTS_SHOP_DAO.selectProductById(products.get(index));
            boolean isAdded = false;

            if (product != null) {
                isAdded = SPORTS_SHOP_DAO.removeProduct(product);
            }
            map.put(String.format("%s %o","Successfully deleted product ", index + 1), isAdded);
        }
        return map;
    }

    public static RestService getInstance() {
        if (restService == null) {
            restService = new RestServiceImpl();
        }
        return restService;
    }
}
