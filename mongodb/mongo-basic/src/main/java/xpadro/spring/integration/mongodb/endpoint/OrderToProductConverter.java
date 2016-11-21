package xpadro.spring.integration.mongodb.endpoint;

import org.springframework.core.convert.converter.Converter;
import xpadro.spring.integration.mongodb.entity.Order;
import xpadro.spring.integration.mongodb.entity.Product;

public class OrderToProductConverter implements Converter<Order, Product> {

    @Override
    public Product convert(Order order) {
        return new Product(order.getId(), order.isPremium());
    }
}
