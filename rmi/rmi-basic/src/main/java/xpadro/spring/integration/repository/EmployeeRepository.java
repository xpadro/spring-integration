package xpadro.spring.integration.repository;

import xpadro.spring.integration.model.Employee;

public interface EmployeeRepository {
	Employee getEmployee(int id);
}
