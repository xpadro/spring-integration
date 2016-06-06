package xpadro.spring.integration.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import xpadro.spring.integration.model.Employee;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	private JdbcTemplate template;
	private RowMapper<Employee> rowMapper = new EmployeeRowMapper();
	private static final String SEARCH = "select * from employees where id = ?";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "name";
	
	@Autowired
	public EmployeeRepositoryImpl(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	public Employee getEmployee(int id) {
		return template.queryForObject(SEARCH, rowMapper, id);
	}
	
	private class EmployeeRowMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int i) throws SQLException {
			Employee employee = new Employee();
			employee.setId(rs.getInt(COLUMN_ID));
			employee.setName(rs.getString(COLUMN_NAME));
			
			return employee;
		}
	}
}
