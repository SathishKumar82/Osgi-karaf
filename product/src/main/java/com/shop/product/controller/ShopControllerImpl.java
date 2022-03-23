package com.shop.product.controller;

import com.shop.product.model.Product;
import com.shop.product.service.RestService;
import com.shop.product.service.RestServiceImpl;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Shop controller implementation.
 *
 * @author SathishKumarS
 */
@Component(service = ShopController.class, immediate = true)
public class ShopControllerImpl implements ShopController {

    private static final RestService REST_SERVICE = RestServiceImpl.getInstance();

    /**
     * adds the product.
     *
     * @param products
     * @return Boolean
     */
    @Override
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Map<String, Boolean> addProduct(final List<Product> products) {
        return REST_SERVICE.addProduct(products);
    }

    /**
     * Selects product.
     *
     * @param brand
     * @param name
     * @param size
     *
     * @return Product
     */
    @Override
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List selectProduct(@QueryParam("brand") final String brand, @QueryParam("name") final String name, @QueryParam("size") final char size) {
        final Product product = new Product();

        if (brand != null && name != null && size != 0) {
            product.setBrand(brand);
            product.setName(name);
            product.setSize(size);

            return REST_SERVICE.selectProduct(product);
        } else {
            final List invalid = new ArrayList();

            invalid.add("Please Mention Brand, Name And Size");
            return invalid;
        }
    }

    /**
     * Deletes the product.
     *
     * @param products
     *
     * @return Boolean.
     */
    @Override
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public Map<String, Boolean> removeProduct(final List<Product> products) {
        return REST_SERVICE.removeProduct(products);
    }

    /**
     * Updates the product.
     *
     * @param products
     *
     * @return Boolean.
     */
    @Override
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Map<String, Boolean> updateProduct(final List<Product> products) {
        return REST_SERVICE.updateProductPrice(products);
    }

    /**
     * Selects all the products.
     *
     * @param page
     * @param noOfEntities
     *
     * @return List ofe products.
     */
    @Override
    @Path("/selectall")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map selectAllProduct(@QueryParam("page") final int page, @DefaultValue("5") @QueryParam("limit") final int noOfEntities) {
        return REST_SERVICE.selectAllProducts(page, noOfEntities);
    }
}
