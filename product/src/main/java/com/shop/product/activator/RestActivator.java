package com.shop.product.activator;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import com.shop.product.controller.ShopController;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Rest activator.
 *
 * @author SathishKumarS
 */
@Component(immediate = true)
public class RestActivator {

    @Reference
    private ShopController shopController;
    private static Server server;

    /**
     * To activate the rest service.
     */
    @Activate
    public void activate() {
        System.out.println("Activated");

         try {
             final JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();

             factoryBean.setAddress("/shop");
             factoryBean.setBus(BusFactory.getDefaultBus());
             factoryBean.setProvider(new JacksonJsonProvider());
             factoryBean.setServiceBean(shopController);

             server = factoryBean.create();
         } catch (Exception exception) {
             System.out.println(exception);
         }
    }

    @Deactivate
    public void deactivate() {
        if (server != null) {
            server.destroy();
        }
    }
}
