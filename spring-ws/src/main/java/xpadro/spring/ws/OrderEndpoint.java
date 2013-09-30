package xpadro.spring.ws;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xpadro.spring.ws.exception.OrderFormatDateException;
import xpadro.spring.ws.model.OrderConfirmation;
import xpadro.spring.ws.service.OrderService;
import xpadro.spring.ws.types.ClientDataRequest;
import xpadro.spring.ws.types.ClientDataResponse;

@Endpoint
public class OrderEndpoint {

	@Autowired
	private OrderService orderService;
	
	@PayloadRoot(localPart="clientDataRequest", namespace="http://www.xpadro.spring.samples.com/orders")
	public @ResponsePayload ClientDataResponse order(@RequestPayload ClientDataRequest orderData) {
		OrderConfirmation confirmation = 
				orderService.order(orderData.getClientId(), orderData.getProductId(), orderData.getQuantity().intValue());
		
		ClientDataResponse response = new ClientDataResponse();
		response.setConfirmationId(confirmation.getConfirmationId());
		BigDecimal amount = new BigDecimal(Float.toString(confirmation.getAmount()));
		response.setAmount(amount);
		response.setOrderDate(convertDate(confirmation.getOrderDate()));
		
		return response;
	}
	
	private XMLGregorianCalendar convertDate(Date serviceDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(serviceDate);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		} catch (DatatypeConfigurationException e) {
			throw new OrderFormatDateException();
		}
	}
}
