package com.shop.validations.datevalidation;

import com.shop.validations.exceptions.InvalidDateException;

import java.time.LocalDate;

/**
 * Validates the user input data.
 * 
 * @author SathishKumarS
 * 
 */
public class DateValidation {

	/**
     * Validates the manufacture date of product. 
     * 
     * @param inputDate
     * @return 
     */
    public static boolean validateDate(final String inputDate) {
		
        try {
            final LocalDate date = LocalDate.parse(inputDate);
            final LocalDate todayDate = LocalDate.now();
            
            return todayDate.plusDays(1).isAfter(date)? true : false;
        } catch(Exception e) {
            throw new InvalidDateException("Date Format Is Invalid \n Re-Enter Valid Date");
        }
    }
}
