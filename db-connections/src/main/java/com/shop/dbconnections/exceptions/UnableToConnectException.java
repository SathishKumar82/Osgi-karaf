package com.shop.dbconnections.exceptions;

import com.shop.customexceptions.CustomException;

/**
 * Unable to connect exception.
 *
 * @author SathishKumarS
 */
public class UnableToConnectException extends CustomException {

    public UnableToConnectException(final String message) {
        super(message);
    }
}
