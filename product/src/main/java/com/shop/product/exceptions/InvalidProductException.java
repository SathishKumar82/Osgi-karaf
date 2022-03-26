package com.shop.product.exceptions;

import com.shop.customexceptions.CustomException;

/**
 * Invalid product exception.
 *
 * @author SathishKumarS
 */
public class InvalidProductException extends CustomException {

    public InvalidProductException(final String message) {
        super(message);
    }
}
