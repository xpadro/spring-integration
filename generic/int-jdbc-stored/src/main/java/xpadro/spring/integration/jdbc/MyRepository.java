package xpadro.spring.integration.jdbc;

import xpadro.spring.integration.jdbc.model.Order;

public interface MyRepository {

	Order getOrder(int id);
	
	String getDescriptionFromStoredProcedure(int id);
}
