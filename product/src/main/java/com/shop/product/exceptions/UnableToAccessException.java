package com.shop.product.exceptions;

import com.shop.customexceptions.CustomException;

/**
 * Unable to access exception.
 *
 * @author SathishKumarS
 */
public class UnableToAccessException extends CustomException {

    public UnableToAccessException(final String message) {
        super(message);
    }
}
