package xpadro.spring.integration.service;

import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;

import xpadro.spring.integration.model.Order;
import xpadro.spring.integration.model.OrderConfirmation;

/**
 * Contains sync and async methods as entry to the messaging system
 * 
 * @author xpadro
 *
 */
public interface OrderService {
	@Gateway
	public OrderConfirmation sendOrder(Order order);
	
	@Gateway
	public Future<OrderConfirmation> sendFutureOrder(Order order);
}
