package xpadro.spring.integration.jdbc;

import xpadro.spring.integration.jdbc.model.Order;

public interface MyRepository {

	public Order getOrder(int id);
	
	public String getDescriptionFromStoredProcedure(int id);
}
