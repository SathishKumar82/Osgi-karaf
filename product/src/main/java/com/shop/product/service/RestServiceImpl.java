package com.shop.product.service;

import com.shop.product.dao.SportsShopDao;
import com.shop.product.dao.SportsShopDaoImpl;
import com.shop.product.model.*;

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
        final Map map = new HashMap();

        for (int index = 0; index < products.size(); index++) {
            final Product product = products.get(index);
            final List list = new ProductValidator().validate(product, AddCheck.class);

            if (list.isEmpty()) {
                map.put(String.format("%s %o", "Product add status ", index + 1), SPORTS_SHOP_DAO.addProduct(product));
            } else {
                map.put(String.format("%s %o","message ", index + 1), list);
            }
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
        } else if (page == 0 && noOfEntities == 5) {
            response.put("total", products.size());
            response.put("count", products.size());
            response.put("data", products);

            return response;
        } else if (page == 0) {
            response.put("response", "Page Not Found");
            return response;
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
            response.put("response", "Page Not Found");
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
    public Map selectProduct(final Product product) {
        final Map map = new HashMap();
        final List list = new ProductValidator().validate(product, SelectCheck.class);

        if (list.isEmpty()) {
            final Product selectedProduct = SPORTS_SHOP_DAO.selectProductById(product);

            if (selectedProduct == null) {
                map.put("message", "Product Not Available");
            } else {
                map.put("data", selectedProduct);
            }
        } else {
            map.put("message", list);
        }
        return map;
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
            final Product product = products.get(index);
            final List list = new ProductValidator().validate(product, UpdateCheck.class);

            if (list.isEmpty()) {
                map.put(String.format("%s %o", "Product update status ", index + 1),
                        SPORTS_SHOP_DAO.updateProductPrice(product));
            } else {
                map.put(String.format("%s %o","message ", index + 1), list);
            }
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
            final Product product = products.get(index);
            final List list = new ProductValidator().validate(product, DeleteCheck.class);

            if (list.isEmpty()) {
                Product selectedProduct = SPORTS_SHOP_DAO.selectProductById(product);

                if (selectedProduct != null) {
                    map.put(String.format("%s %o", "Product delete status ", index + 1),
                            SPORTS_SHOP_DAO.removeProduct(product));
                } else {
                    map.put("message", "product not in crew");
                }
            } else {
                map.put(String.format("%s %o","message ", index + 1), list);
            }
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
