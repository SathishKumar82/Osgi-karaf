package com.shop.product.view;

import com.shop.inputgetter.InputGetter;
import com.shop.product.activator.Activator;
import com.shop.product.attributes.Choice;
import com.shop.product.attributes.ProductAttributes;
import com.shop.product.controller.ShopKeeper;
import com.shop.product.exceptions.InvalidProductException;
import com.shop.product.exceptions.UnableToAccessException;
import com.shop.product.model.Product;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The shop application for sports-kits using CRUD operations - Create, Read, Update, Delete.
 * 
 *  @author SathishKumarS
 *  
 */
public class SportsShop  {

    static final ShopKeeper SHOP_KEEPER = ShopKeeper.getInstance();
    static final Logger LOGGER = Logger.getLogger(SportsShop.class);

    /**
     * Main menu.
     * 
     */
    public static void menu() {
        int operation = 0;

        do {
        	System.out.println("\n\n    <^_^> Sports Shop Application <^_^>\n\n Operations \n  1.Add Product \n  2.Select Product \n  3.Update Product Price \n  4.Remove Product \n  5.Select All Products \n  6.Exit");
            operation = Choice.getOperation();

            switch (operation) {
            case 1:
                SportsShop.addProduct();
                break;
            case 2:
                SportsShop.selectAnyProduct();
                break;
            case 3:
                SportsShop.updateProductPrice();
                break;
            case 4:
                SportsShop.removeProduct();
                break;
            case 5:
            	SportsShop.showAllProducts();
            	break;
            case 6:
                InputGetter.SCANNER.close();

                try {
                    Activator.bundle.stop();
                } catch (Exception exception) {
                    LOGGER.error(exception);
                }
            }
        } while (operation < 6);
    }

    /**
     * Adds the new product.
     *   
     */
    private static void addProduct() {
        final Product product = new Product();
        
        product.setBrand(ProductAttributes.getBrand());
        product.setName(ProductAttributes.getName());
        product.setPrice(ProductAttributes.getPrice());
        product.setSize(ProductAttributes.getSize());
        product.setManufactureDate(ProductAttributes.getManufactureDate());

        try {
            SHOP_KEEPER.addProduct(product);
            LOGGER.info("\n Product Added Successfully");
        } catch (InvalidProductException exception) {
            LOGGER.warn(exception);
        } catch (UnableToAccessException exception) {
            LOGGER.error(exception);
        }
    }

    /**
     * Call the customer for selects any product.
     *  
     */
    private static void selectAnyProduct() {
    	System.out.println("\n Select Any Product");
        final Customer customer = new Customer();

        customer.selectProduct();
    }

    /**
     * Updates the product price. 
     * 
     */
    private static void updateProductPrice() {
        final Product product = new Product();
        
        product.setBrand(ProductAttributes.getBrand());
        product.setName(ProductAttributes.getName());
        product.setSize(ProductAttributes.getSize());
        
        try {
            SHOP_KEEPER.selectProduct(product);
        } catch (InvalidProductException exception) {
            LOGGER.warn(exception);
        	updateProductPrice();
        	menu();
        } catch (UnableToAccessException exception) {
            LOGGER.error(exception);
        }
        product.setPrice(ProductAttributes.getPrice());
        
        try {
            SHOP_KEEPER.updateProductPrice(product);
            LOGGER.info("\n Product Updated Successfully");
        } catch (InvalidProductException exception) {
            LOGGER.warn(exception);
        } catch (UnableToAccessException exception) {
            LOGGER.error(exception);
        }
    }

    /**
     * Removes the product.
     * 
     */
    private static void removeProduct() {
        final Product product = new Product();
        
        product.setBrand(ProductAttributes.getBrand());
        product.setName(ProductAttributes.getName());
        product.setSize(ProductAttributes.getSize());

        try {
            SHOP_KEEPER.removeProduct(product);
            LOGGER.info("\n Product Removed Successfully");
        } catch (InvalidProductException exception) {
            LOGGER.warn(exception);
        } catch (UnableToAccessException exception) {
            LOGGER.error(exception);
        }
    }
    
    /**
     * Shows all the available products.
     * 
     */
    private static void showAllProducts() {
    	List<Product> products = null;
    	
    	try {
    		products = SHOP_KEEPER.selectAllProducts();
    	} catch (InvalidProductException exception) {
            LOGGER.warn(exception);
        } catch (UnableToAccessException exception) {
            LOGGER.error(exception);
        }
    	
        if (products != null) {

            for (Product product : products) {
            	System.out.println(String.format("%s %s %s %s %s %s %f %s %c %s %s", "\n\n ", product.getBrand(),
                    "Brand :", "\n   Product Name :", product.getName(), "\n   Product Price :", product.getPrice(),
                    "\n   Product Size :", product.getSize(), "\n   Manufacture Date :", product.getManufactureDate().toString()));
            }
        }
    }

    /**
     * Shows the Selected Product. 
     * 
     * @param product
     * 
     */
    public static void showProduct(final Product product) {

        if (product != null) {
        	System.out.println(String.format("%s %s %s %s %s %f %s %c %s %s","\n\n Product Details : \n  Product Name :",
                product.getName(), "\n  Product Brand :", product.getBrand(), "\n  Product Price :",
                product.getPrice(), "\n  Product Size :", product.getSize(), "\n  Manufacture Date :",
                product.getManufactureDate()).toString());
        }
    }
}
