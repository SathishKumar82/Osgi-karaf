package com.shop.validations.exceptions;

import com.shop.customexceptions.CustomException;

/**
 * Invalid date exception.
 *
 * @author SathishKumarS
 */
public class InvalidDateException extends CustomException {

    public InvalidDateException(final String message) {
        super(message);
    }
}
