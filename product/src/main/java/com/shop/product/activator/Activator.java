package com.shop.product.activator;

import com.shop.product.view.SportsShop;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator.
 *
 * @author SathishKumarS
 */
public class Activator implements BundleActivator {
    public static Bundle bundle;

    public void start(BundleContext context) {
        bundle = context.getBundle();
        System.out.println("Starting view bundle");

        SportsShop.menu();
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle");
    }

}