package com.shop.product.attributes;

import com.shop.inputgetter.InputGetter;
import com.shop.product.view.SportsShop;
import com.shop.validations.datevalidation.DateValidation;

import java.sql.Date;

/**
 * Product attributes
 *
 * @author SathishKumarS
 */
public class ProductAttributes {

    static final InputGetter INPUT_GETTER = InputGetter.getInstance();

    /**
     * Gets the product brand.
     *
     * @return
     *
     */
    public static String getBrand() {
        final String brand = INPUT_GETTER.getString("\n Mention Product Brand(SS, SG, MRF, RBK, NIKE) |--> If You Want To Go Back To Main Menu, Press #");

        ProductAttributes.goBackToMenu(brand);

        if (brand.matches("(?i)ss|sg|mrf|nike")) {
            return brand.toUpperCase();
        }
        System.out.println("/n Brand Is Not Available \n  Select Anyone Of Them (SS, SG, MRF, RBK, NIKE)");
        return getBrand();
    }

    /**
     * Gets the product name.
     *
     * @return
     *
     */
    public static String getName() {
        final String name = INPUT_GETTER.getString("\n Mention Product Name(Bat, Ball, Stump, Gloves, Helmet) |--> If You Want To Go Back To Main Menu, Press #");

        ProductAttributes.goBackToMenu(name);

        if (name.matches("(?i)bat|ball|stump|gloves|helmet")) {
            return name.toUpperCase();
        }
        System.out.println("\n Product Is Not Available \n  Select Anyone Of Them (Bat, Ball, Stump, Gloves, Helmet)");
        return getName();
    }

    /**
     * Gets the product price.
     *
     * @return
     *
     */
    public static double getPrice() {
        final String price = INPUT_GETTER.getString("\n Mention Product Price |--> If You Want To Go Back To Main Menu, Press #");

        ProductAttributes.goBackToMenu(price);

        if (price.matches("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)")) {
            return Double.parseDouble(price);
        }
        System.out.println("/n Price Is Invalid \n  Re-Enter Valid Price(Use Whole Numbers and Decimal Numbers Only)");
        return getPrice();
    }

    /**
     * Gets the product size.
     *
     * @return
     *
     */
    public static char getSize() {
        final String size = INPUT_GETTER.getString("\n Mention Product Size(S, M, L) |--> If You Want To Go Back To Main Menu, Press #");

        ProductAttributes.goBackToMenu(size);

        if (size.matches("(?i)s|m|l")) {
            return size.toUpperCase().charAt(0);
        }
        System.out.println("\n Size Is Invalid \n  Re-Enter Valid Size(Use Only S, M, L)");
        return getSize();
    }

    /**
     * Gets the product's manufacture date.
     *
     * @return
     *
     */
    public static Date getManufactureDate() {
        final String date = INPUT_GETTER.getString("\n Mention Manufacture Date(YYYY-MM-DD) |--> If You Want To Go Back To Main Menu, Press #");

        ProductAttributes.goBackToMenu(date);

        if (DateValidation.validateDate(date)) {
            return Date.valueOf(date);
        }
        System.out.println("\n Date Is Invalid \n  Re-Enter Valid Date");
        return getManufactureDate();
    }

    /**
     * Goes back to menu.
     *
     * @param userInput
     *
     */
    private static void goBackToMenu(final String userInput) {
        if ("#".equals(userInput)) {
            SportsShop.menu();
        }
    }
}
