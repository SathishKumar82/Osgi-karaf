package com.shop.product.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Product attributes validator.
 *
 * @author SathishKumarS
 */
public class ProductValidator {

    private ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
    private Validator validator = factory.getValidator();

    /**
     * Validates the product attributes.
     *
     * @param product
     * @param clazz
     *
     * @return list
     */
    public List validate(Product product, Class clazz) {
        final List list = new ArrayList();
        Set<ConstraintViolation<Product>> violations = validator.validate(product, clazz);

        for (ConstraintViolation<Product> violation : violations) {
            list.add(violation.getMessage());
        }
        factory.close();
        return list;
    }
}
