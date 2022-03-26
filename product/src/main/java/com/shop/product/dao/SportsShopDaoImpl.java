package com.shop.product.dao;

import com.shop.dbconnections.connections.DBConnections;
import com.shop.product.exceptions.UnableToAccessException;
import com.shop.product.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * It access the database.
 * 
 * @author SathishKumarS
 *
 */
public class SportsShopDaoImpl implements SportsShopDao {

    private static final DBConnections DB_CONNECTIONS = new DBConnections();
    private static final String INSERT_PRODUCT = "insert into products (brand, name, price, size, manufacturedate, isdeleted) values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PRODUCT = "select brand, name, price, size, manufacturedate from products where isdeleted = ?";
    private static final String SELECT_PRODUCT_BY_ID = "select brand, name, price, size, manufacturedate from products where brand = ? and name = ? and size = ? and isdeleted = ?";
    private static final String DELETE_PRODUCT = "update products set isdeleted = ? where brand = ? and name = ? and size = ?";
    private static final String UPDATE_PRODUCT = "update products set price = ? where brand = ? and name = ? and size = ? where isdeleted = ?";
    private static SportsShopDao sportsShopDao;

    private SportsShopDaoImpl() {

    }

    /**
     * Adds the product in database.
     * 
     * @param product
     * @return 
     * 
     */
    public boolean addProduct(final Product product) {

        try (Connection connection = DB_CONNECTIONS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);) {
            preparedStatement.setString(1, product.getBrand());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, String.valueOf(product.getSize()));
            preparedStatement.setDate(5, product.getManufactureDate());
            preparedStatement.setBoolean(6, false);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UnableToAccessException("Couldn't Add The Product... \n    Please Try Again");
        }
    }

    /**
     * Updates the product price.
     * 
     * @param product
     * @return
     * 
     */
    public boolean updateProductPrice(final Product product) {

        try (Connection connection = DB_CONNECTIONS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);) {
            preparedStatement.setDouble(1, product.getPrice());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setString(3, product.getName());
            preparedStatement.setString(4, String.valueOf(product.getSize()));
            preparedStatement.setBoolean(5, false);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UnableToAccessException("Couldn't Update The Product... \n    Please Try Again");
        }
    }

    /**
     * Removes the product from database.
     * 
     * @param product
     * @return
     * 
     */
    public boolean removeProduct(final Product product) {

        try (Connection connection = DB_CONNECTIONS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setString(3, product.getName());
            preparedStatement.setString(4, String.valueOf(product.getSize()));

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UnableToAccessException("Couldn't Remove The Product... \n    Please Try Again");
        }
    }

    /**
     * Retrieves all the products from database.
     * 
     * @return all products by list.
     * 
     */
    public List<Product> selectAllProducts() {
    	
        try (Connection connection = DB_CONNECTIONS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
            final List<Product> products = new ArrayList<Product>();
            
            preparedStatement.setBoolean(1, false);
            
            try (ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    final Product product = new Product(resultSet.getString("name"), resultSet.getString("brand"), 
                		    resultSet.getDouble("price"), resultSet.getString("size").charAt(0), resultSet.getDate("manufacturedate"));

                    products.add(product);
                }
            }
            return products;
        } catch (SQLException exception) {
            throw new UnableToAccessException("Couldn't Selects The Products... \n    Please Try Again");
        }
    }

    public Product selectProductById(final Product product) {

        try (Connection connection = DB_CONNECTIONS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setString(1, product.getBrand());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, String.valueOf(product.getSize()));
            preparedStatement.setBoolean(4, false);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    final Product selectedProduct = new Product(resultSet.getString("name"),
                            resultSet.getString("brand"),
                            resultSet.getDouble("price"),
                            resultSet.getString("size").charAt(0),
                            resultSet.getDate("manufacturedate"));

                    return selectedProduct;
                }
            }
        } catch (Exception exception) {
            throw new UnableToAccessException("Couldn't Selects The Products... \n    Please Try Again");
        }
        return null;
    }

    public static SportsShopDao getInstance() {
        if (sportsShopDao == null) {
            sportsShopDao = new SportsShopDaoImpl();
        }
        return sportsShopDao;
    }
}
