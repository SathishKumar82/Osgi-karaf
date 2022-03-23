package com.shop.customexceptions;

/**
 * Custom Exceptions.
 *
 * @author SathishKumarS
 */
public class CustomException extends RuntimeException {
	
    public CustomException(final String message) {
        super(message);
    }
}
