package com.shop.product.attributes;

/**
 * Get the choice
 *
 * @author SathishKumarS
 */
public class Choice {

    /**
     * Gets the operation.
     *
     * @return
     *
     */
    public static int getOperation() {
        final String operation = String.valueOf(ProductAttributes.INPUT_GETTER.getInt("Select Any Operation"));

        if (operation.matches("[1-6]")) {
            return Integer.parseInt(operation);
        }
        System.out.println("\n Selected Operation Is Invalid \n  Re-Enter Valid Operation(Use Only 1-6)");
        return getOperation();
    }
}
