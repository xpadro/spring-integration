package xpadro.spring.integration.mongodb.endpoint;

import xpadro.spring.integration.mongodb.entity.Product;

public class ProductProcessor {

    public Product process(Product product) {
        return doProcess(product, String.format("Processing product %s", product.getId()));
    }

    public Product fastProcess(Product product) {
        return doProcess(product, String.format("Fast processing product %s", product.getId()));
    }

    private Product doProcess(Product product, String message) {
        System.out.println(message);
        product.setProcessed(true);
        return product;
    }
}
