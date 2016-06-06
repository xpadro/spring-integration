package xpadro.spring.integration.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import xpadro.spring.integration.jdbc.model.Order;

@Repository
public class MyRepositoryImpl implements MyRepository {

	private JdbcTemplate template;
	private RowMapper<Order> rowMapper = new OrderRowMapper();
	private static final String SEARCH = "select * from orders where orderId = ?";
	private static final String COLUMN_ID = "orderId";
	private static final String COLUMN_DESC = "description";
	
	private OrderProcedure orderProcedure;


	@Autowired
	public MyRepositoryImpl(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
		this.orderProcedure = new OrderProcedure(dataSource);
	}

	public Order getOrder(int id) {
		return template.queryForObject(SEARCH, rowMapper, id);
	}

	private class OrderRowMapper implements RowMapper<Order> {
		public Order mapRow(ResultSet rs, int i) throws SQLException {
			return new Order(rs.getInt(COLUMN_ID), rs.getString(COLUMN_DESC));
		}
	}

	@Override
	public String getDescriptionFromStoredProcedure(int id) {
		return orderProcedure.executeQuery(id);
	}
	
	
	class OrderProcedure extends StoredProcedure {
		private static final String PROCEDURE_NAME = "get_order";
		private static final String PARAM_ID = "id";
		private static final String PARAM_DESCRIPTION = "descrip";
		
		public OrderProcedure(DataSource dataSource) {
			super(dataSource, PROCEDURE_NAME);
			this.declareParameter(new SqlParameter(PARAM_ID, Types.INTEGER));
			this.declareParameter( new SqlOutParameter(PARAM_DESCRIPTION, Types.VARCHAR));
		}
		
		public String executeQuery(int orderId){
			Map<String,Object> results = super.execute(orderId);
			return (String) results.get(PARAM_DESCRIPTION);
		}
	}
}
