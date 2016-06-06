package xpadro.spring.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xpadro.spring.integration.model.Employee;
import xpadro.spring.integration.repository.EmployeeRepository;

@Service("defaultEmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee retrieveEmployee(int id) {
		return employeeRepository.getEmployee(id);
	}
}
